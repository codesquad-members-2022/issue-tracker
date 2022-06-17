//
//  LoginViewModel.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/15.
//

import Foundation

// 네트워크 작업을 일으키는 메소드
protocol LoginViewModelInput {}

// 네트워크 작업에 대한 결과로 변경되는 데이터
protocol LoginViewModelOutput {
    var isAuthenticated: Observable<Bool> { get }
}

protocol LoginViewModel: LoginViewModelInput, LoginViewModelOutput {
    var input: LoginViewModelInput { get }
    var output: LoginViewModelOutput { get }

    func getURL() -> URL?
    func showMainScene()
}

protocol LoginFlowAction {
    var goToMainScene: () -> Void { get }
}

struct LoginViewModelAction: LoginFlowAction {
    var goToMainScene: () -> Void
}

struct DefaultLoginViewModel: LoginViewModel {
    var input: LoginViewModelInput { self }
    var output: LoginViewModelOutput { self }

    private let gitHubURL = URL(string: "https://")

    var isAuthenticated = Observable(false)

    private let navigationAction: LoginFlowAction

    init(navigationAction: LoginFlowAction) {
        self.navigationAction = navigationAction
    }

    func getURL() -> URL? {
        gitHubURL
    }
}

// MARK: - Navigation
extension DefaultLoginViewModel {
    func showMainScene() {
        navigationAction.goToMainScene()
    }
}
