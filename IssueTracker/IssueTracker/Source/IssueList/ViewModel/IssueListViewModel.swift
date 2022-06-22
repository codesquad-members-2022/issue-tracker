//
//  IssueListViewModel.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/21.
//

import Foundation

final class IssueListViewModel {

    var issueList: [Issue] = [Issue]()
    var issueRepository = IssueTrackingRepository()

    var numberOfItemsInSection: Int {
        return issueList.count
    }

    var loginSuccess: (() -> Void)?
    var loginFailure: (() -> Void)?

    func loadIssueList() {
        guard let token = UserDefaults.standard.object(forKey: Environment.token) as? String else { return }
        let target = IssueTrackerTarget.requestIssueList(token: token)
        issueRepository.requestIssues(with: target) { response in
            switch response {
            case .success(let issue):
                self.issueList = issue ?? [Issue]()
                self.loginSuccess?()
            case .failure(let error):
                print(error)
                self.loginFailure?()
            }
        }
    }

}
