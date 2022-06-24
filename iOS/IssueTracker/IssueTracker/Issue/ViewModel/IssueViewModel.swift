//
//  IssueViewModel.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/23.
//

import Foundation

protocol IssueViewModelOutput {
    var list: Observable<[IssueItem]> { get }
}

class IssueViewModel: IssueViewModelOutput {

    private var issueManager = IssueManager()
    
    init(issueManager: IssueManager) {
        self.issueManager = issueManager
    }
    
    var list: Observable<[IssueItem]> = Observable([IssueItem]())
    var cellCount: Int { list.value.count }
    
    subscript(index: Int) -> IssueItem? {
        guard index < list.value.count else { return nil }
        return list.value[index]
    }
    
}

// MARK: - Request Order List
extension IssueViewModel {
    func loadFromIssueManager() {
        issueManager.load { issueItemList in
            self.list.value = issueItemList
        }
    }
}
