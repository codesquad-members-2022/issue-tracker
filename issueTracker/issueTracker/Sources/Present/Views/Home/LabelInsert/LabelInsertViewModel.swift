//
//  LabelInsertViewModel.swift
//  issueTracker
//
//  Created by 이준우 on 2022/06/15.
//
import Foundation
import RxRelay
import RxSwift

final class LabelInsertViewModel: ViewModel {
    
    private weak var navigation: LabelListNavigation?
    
    private enum Constants {
        static let owner = "shingha1124"
        static let repo = "issue-tracker"
    }
    
    struct Action {
        let enteredTitleValue = PublishRelay<String>()
        let enteredDescriptionValue = PublishRelay<String>()
        let tappedColorChangeButton = PublishRelay<Void>()
        let requestedCreatingLabel = PublishRelay<Void>()
    }
    
    struct State {
        let updatedTitleValue = PublishRelay<String>()
        let updatedDescriptionValue = PublishRelay<String>()
        let updatedRgbValue = PublishRelay<String>()
    }
    
    let action = Action()
    let state = State()
    private let disposeBag = DisposeBag()
    var randomColor: String {
        let randomList = ["c5def5", "7FAD7D", "320F8D", "F6CBD5"]
        return randomList.randomElement() ?? ""
    }
    
    @Inject(\.gitHubRepository) private var gitHubRepository: GitHubRepository
    
    init(navigation: LabelListNavigation) {
        self.navigation = navigation
        
        //Action 속성과 State 속성 바인딩
        bindActionsToStates()
        //State 속성과 라벨 생성 요청 바인딩
        bindStatesToCreatingRequest()
    }
    
    private func bindActionsToStates() {
        action.enteredTitleValue
            .bind(to: state.updatedTitleValue)
            .disposed(by: disposeBag)
        
        action.enteredDescriptionValue
            .bind(to: state.updatedDescriptionValue)
            .disposed(by: disposeBag)
        
        action.tappedColorChangeButton
            .withUnretained(self)
            .map { viewModel, _ in viewModel.randomColor }
            .bind(to: state.updatedRgbValue)
            .disposed(by: disposeBag)
    }
    
    private func bindStatesToCreatingRequest() {
        let parameters = Observable
            .combineLatest(state.updatedRgbValue, state.updatedDescriptionValue, state.updatedTitleValue) { color, description, title in
                ["name": title, "description": description, "color": color]
            }
            .share()
        
        action.requestedCreatingLabel
            .withLatestFrom(parameters)
            .map { param in
                RequestCreatingLabel(owner: Constants.owner, repo: Constants.repo, parameters: param)
            }
            .withUnretained(self)
            .flatMapLatest { viewModel, parameters in
                viewModel.gitHubRepository.requestCreatingLabel(parameters: parameters)
            }
            .compactMap { $0.value }
            .bind(onNext: { result in
                Log.debug("\(result)")
            })
            .disposed(by: disposeBag)
    }
}
