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
    func showLoginScene(type: LoginType)
    func fetchToken()
}

// 코디네이터가 의존성 주입할 네비게이션 액션 핸들러
protocol LoginFlowAction {
    var showMainScene: () -> Void { get }
    var showGithubLoginScene: () -> Void { get }
    var showAppleLoginScene: () -> Void { get }
}

struct LoginViewModelAction: LoginFlowAction {
    var showMainScene: () -> Void
    var showGithubLoginScene: () -> Void
    var showAppleLoginScene: () -> Void
}

enum LoginType {
    case github
    case apple
}

struct DefaultLoginViewModel: LoginViewModel {
    var input: LoginViewModelInput { self }
    var output: LoginViewModelOutput { self }

    private let gitHubURL = URL(string: "https://")
    private let useCase = Usecase(loginRepository: Repository())

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
        navigationAction.showMainScene()
    }

    func showLoginScene(type: LoginType) {
        switch type {
        case .github:
            return navigationAction.showGithubLoginScene()
        case .apple:
            return navigationAction.showAppleLoginScene()
        @unknown case _:
            return
        }
    }

    func fetchToken() {
        useCase.start { result in
            if result {
                navigationAction.showMainScene()
            }
        }
    }
}
