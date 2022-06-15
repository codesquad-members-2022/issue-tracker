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

    struct Action {
        let enteredTitleValue = PublishRelay<String>()
        let enteredDescriptionValue = PublishRelay<String>()
        let tappedColorChangeButton = PublishRelay<Void>()
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
        let randomList = ["#c5def5", "#7FAD7D", "#320F8D", "#F6CBD5"]
        return randomList.randomElement() ?? ""
    }

    init(navigation: LabelListNavigation) {
        self.navigation = navigation
        
        action.enteredTitleValue
            .bind(to: state.updatedTitleValue)
            .disposed(by: disposeBag)
        
        action.enteredDescriptionValue
            .bind(to: state.updatedDescriptionValue)
            .disposed(by: disposeBag)
        
        action.tappedColorChangeButton
            .withUnretained(self)
            .map { vm, _ in String(vm.randomColor.dropFirst()) }
            .bind(to: state.updatedRgbValue)
            .disposed(by: disposeBag)
    }
}
