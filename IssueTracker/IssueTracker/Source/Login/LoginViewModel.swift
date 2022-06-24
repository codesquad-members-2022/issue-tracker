//
//  LoginViewModel.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/22.
//

import Foundation

final class LoginViewModel {

    let repository: LoginRepository

    init(repository: LoginRepository) {
        self.repository = repository
    }

    func excuteLogin() {
        repository.requestGithubAuthorize()
    }
}
