//
//  LoginViewModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation

final class LoginViewModel {
    func requestGithubLogin() {
        let githubLoginManager = GitHubLoginManager()
        githubLoginManager.requestAuthorization()
    }
}
