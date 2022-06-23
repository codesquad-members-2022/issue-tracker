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
        guard let token = UserDefaults
                .standard.object(forKey: Environment.token) as? String else {
                    // MAKR: 로그인하고 보여지는 화면은 무조건 LoginVC가 나옴
                    /// 1. 앱 처음 구동 -> self.loginFailure?()
                    /// 2.
                    self.loginFailure?()
                    return
                }
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
