//
//  LoginViewModel.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/15.
//

import Foundation

protocol LoginViewModelInput {}

protocol LoginViewModelOutput {}

protocol LoginViewModel: LoginViewModelInput, LoginViewModelOutput {
    var input: LoginViewModelInput { get }
    var output: LoginViewModelOutput { get }
}

struct LoginViewModelAction {}

struct DefaultLoginViewModel: LoginViewModel {
    var input: LoginViewModelInput { self }
    var output: LoginViewModelOutput { self }
}
