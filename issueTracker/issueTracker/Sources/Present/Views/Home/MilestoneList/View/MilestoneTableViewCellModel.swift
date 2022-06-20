//
//  MilestoneTableViewModel.swift
//  issueTracker
//
//  Created by 이준우 on 2022/06/20.
//

import Foundation
import RxRelay
import RxSwift

final class MilestoneTableViewCellModel: ViewModel {
    
    struct Action {
        let loadData = PublishRelay<Void>()
    }
    
    struct State {
        let milestone: Milestone
        let title = PublishRelay<String>()
        let description = PublishRelay<String>()
        let deadline = PublishRelay<Date?>()
        let openedIssueCount = PublishRelay<Int>()
        let closedIssueCount = PublishRelay<Int>()
    }
    
    let action = Action()
    let state: State
    private let disposeBag = DisposeBag()
    
    init(milestone: Milestone) {
        state = State(milestone: milestone)
        
        action.loadData
            .map { milestone.title }
            .bind(to: state.title)
            .disposed(by: disposeBag)

        action.loadData
            .map { milestone.description }
            .bind(to: state.description)
            .disposed(by: disposeBag)

        action.loadData
            .map { milestone.deadline }
            .bind(to: state.deadline)
            .disposed(by: disposeBag)

        action.loadData
            .map { milestone.openedIssueCount }
            .bind(to: state.openedIssueCount)
            .disposed(by: disposeBag)

        action.loadData
            .map { milestone.closedIssueCount }
            .bind(to: state.closedIssueCount)
            .disposed(by: disposeBag)
    }
}
