//
//  UserDefaultManager.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/16.
//

import Foundation

struct UserDefaultManager {
    static func saveJWTToken(_ jwtToken: String) {
        let userDefault = UserDefaults.standard
        userDefault.set(jwtToken, forKey: KeyType.userSignInToken.key)
    }

    static func saveRefreshToken(_ refreshToken: String) {
        let userDefault = UserDefaults.standard
        userDefault.set(refreshToken, forKey: KeyType.userRefreshToken.key)
    }

    static func isSavedJWTToken() -> Bool {
        let userDefault = UserDefaults.standard
        return userDefault.string(forKey: KeyType.userSignInToken.key) != nil
    }

    static func getJWTToken() -> String? {
        let userDefault = UserDefaults.standard
        return userDefault.string(forKey: KeyType.userSignInToken.key)
    }
}

extension UserDefaultManager {
    enum KeyType {
        case userSignInToken
        case userRefreshToken

        var key: String {
            switch self {
            case .userSignInToken:
                return "UserSignInToken"
            case .userRefreshToken:
                return "UserRefreshToken"
            }
        }
    }
}
