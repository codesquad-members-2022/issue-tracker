//
//  IssueListViewModel.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/21.
//

import Foundation

final class IssueListViewModel {

    var issueList: Observable<[Issue]> = Observable([])
    var issueRepository = IssueTrackingRepository()

    var numberOfItemsInSection: Int {
        return issueList.value.count
    }

    func loadIssueList() {
        let token: String = UserDefaults.standard.object(forKey: Environment.token) as? String ?? ""
        let target = IssueTrackerTarget.requestIssueList(token: token)
        issueRepository.requestIssues(with: target) { response in
            switch response {
            case .success(let issue):
                guard let issue = issue else { return }
                self.issueList = Observable(issue)

            case .failure(let error):
                // TODO: error Alert
                print(error)
            }
        }
    }

}
