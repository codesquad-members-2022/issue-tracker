//
//  RootWindowViewModel.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/23.
//

import Foundation

final class RootWindowViewModel {

    let issueListViewModel = IssueListViewModel()
    let loginRepository = LoginRepository()

    // Status
    var onUpdateLoginSatus: (Bool) -> Void = { _ in  }

    // Action
    func requestLoginStatus() {
        guard let token = UserDefaults.standard.object(forKey: Environment.token) as? String else { self.onUpdateLoginSatus(false)
            return }
        loginRepository.requestLoginStatus(IssueTrackerTarget.requestLoginStatus(token: token)) { [weak self] answer in
            self?.onUpdateLoginSatus(answer)
        }

    }
}
