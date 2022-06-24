//
//  ValidateModel.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/23.
//

import Foundation

enum ValidatedResult {
    case success
    case unValidatedID
    case unValidatePassword
}

struct ValidateModel {
    func validate(type: UserInputType, text: String) -> ValidatedResult {
        switch type {
        case .ID:
            return IDChecker.check(input: text) == true ? .success : .unValidatedID
        case .password:
            return PasswordChecker.check(input: text) == true ? .success : .unValidatePassword
        }
    }
}
