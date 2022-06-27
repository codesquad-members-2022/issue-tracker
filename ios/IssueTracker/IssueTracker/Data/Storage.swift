//
//  Storage.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/23.
//

import Foundation

final class Storage {
    private let userDefaults = UserDefaults.standard

    func getCode() -> String? {
        let code = UserDefaults.standard.string(forKey: LocalStorageConstants.AuthCode)
        return code
    }

    func setToken(token: String) {
        userDefaults.setValue(token, forKey: LocalStorageConstants.AuthToken)
    }

    func deleteCode() {
        userDefaults.removeObject(forKey: LocalStorageConstants.AuthCode)
    }
}
