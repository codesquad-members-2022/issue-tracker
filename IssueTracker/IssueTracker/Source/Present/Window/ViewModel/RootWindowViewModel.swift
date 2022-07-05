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
    let isValidLogin: Observable<Bool> = Observable(false)

    func requestLoginStatus() {
        guard let token = UserDefaults.standard.object(forKey: Environment.token) as? String else {
            self.isValidLogin.value = false
            return }
        loginRepository.requestLoginStatus(IssueTrackerTarget.requestLoginStatus(token: token)) { [weak self] _ in
            self?.isValidLogin.value = true
        }

    }
}
