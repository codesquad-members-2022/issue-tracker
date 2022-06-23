//
//  Usecase.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/23.
//

import Foundation

final class Usecase {
    private let loginRepository: Repository

    init(loginRepository: Repository) {
        self.loginRepository = loginRepository
    }

    func start(completion: @escaping (Bool) -> Void) {
        loginRepository.getToken { token in
            switch token {
            case let .success(tokenBag):
                self.loginRepository.setToken(token: tokenBag) { success in
                    if success {
                        completion(true)
                    }
                }
            case let .failure(error):
                return
            }
        }
    }
}
