//
//  MilestoneInsertViewModel.swift
//  issueTracker
//
//  Created by 이준우 on 2022/06/21.
//

import Foundation
import RxRelay
import RxSwift

protocol MilestoneInsertNavigation: AnyObject {
    func goBackToMilestoneList()
}

final class MilestoneInsertViewModel: ViewModel {
    private enum Constants {
        static let owner = "shingha1124"
        static let repo = "issue-tracker"
    }
    
    struct Action {
        let cancelButtonTapped = PublishRelay<Void>()
        let addButtonTapped = PublishRelay<Void>()
        let enteredTitleValue = PublishRelay<String>()
        let enteredDescriptionValue = PublishRelay<String>()
        let enteredDeadlineValue = PublishRelay<String>()
    }
    
    struct State {
        let updatedTitleValue = PublishRelay<String>()
        let updatedDescriptionValue = PublishRelay<String>()
        let updatedDeadlineValue = PublishRelay<String>()
    }
    
    let action = Action()
    let state = State()
    private let disposeBag = DisposeBag()
    private weak var navigation: MilestoneInsertNavigation?
    
    init(navigation: MilestoneInsertNavigation) {
        self.navigation = navigation
        
        action.cancelButtonTapped
            .withUnretained(self)
            .bind(onNext: { viewModel, _ in
                viewModel.navigation?.goBackToMilestoneList()
            })
            .disposed(by: disposeBag)
        
        action.enteredTitleValue
            .bind(to: state.updatedTitleValue)
            .disposed(by: disposeBag)
        
        action.enteredDescriptionValue
            .bind(to: state.updatedDescriptionValue)
            .disposed(by: disposeBag)
        
        action.enteredDeadlineValue
            .bind(to: state.updatedDeadlineValue)
            .disposed(by: disposeBag)
    }
}
