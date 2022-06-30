//
//  IssueViewModel.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/23.
//

import Foundation

protocol IssueViewModelInput {
    func didAddNewIssue(_ issue: IssueItem)
}

protocol IssueViewModelOutput {
    var list: Observable<[IssueItem]> { get }
}

protocol IssueViewModelProtocol: IssueViewModelInput, IssueViewModelOutput { }

class IssueViewModel: IssueViewModelProtocol {

    private var issueManager: IssueManagable
    
    init(issueManager: IssueManagable) {
        self.issueManager = issueManager
        addObserver()
    }
    
    var list: Observable<[IssueItem]> = Observable([IssueItem]())
    var cellCount: Int { list.value.count }
    
    subscript(index: Int) -> IssueItem? {
        guard index < list.value.count else { return nil }
        return list.value[index]
    }

    func didAddNewIssue(_ issue: IssueItem) {
        list.value.append(issue)
    }
}

// MARK: - Request Order List
extension IssueViewModel {
    func loadFromIssueManager() {
        issueManager.load { [weak self] result in
            switch result {
            case .success(let issueItemList):
                self?.list.value = issueItemList
            case .failure:
                return
            }
        }
    }
}

private extension IssueViewModel {
    func addObserver() {
        NotificationCenter.default.addObserver(forName: EditingIssueViewModel.NotificationNames.didSaveNewIssue, object: nil, queue: nil) { [weak self] notification in
            guard let addedIssue = notification.userInfo?[EditingIssueViewModel.UserInfoKey.addedIssue] as? IssueItem else { return }

            self?.didAddNewIssue(addedIssue)
        }
    }
}
