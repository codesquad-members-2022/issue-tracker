//
//  UseCase.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/23.
//

import Foundation

final class UseCase {
    private let loginRepository: AuthRepository

    init(loginRepository: AuthRepository) {
        self.loginRepository = loginRepository
    }

    func start(completion: @escaping (NetworkError?) -> Void) {
        loginRepository.getToken { token in
            switch token {
            case let .success(tokenBag):
                self.loginRepository.setToken(token: tokenBag.token)
                completion(nil)
            case let .failure(error):
                completion(error)
                return
            }
        }
    }
}
