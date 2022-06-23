//
//  LoginFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/14.
//

import UIKit

protocol LoginFlowCoordinatorDependencies {
    func setAuthCode(code: String)
    func makeLoginViewController(action: LoginViewModelAction) -> UIViewController
}

final class LoginFlowCoordinator: Coordinator {
    private let navigationController: UINavigationController
    private let dependency: LoginFlowCoordinatorDependencies

    init(navigationController: UINavigationController, dependency: LoginFlowCoordinatorDependencies) {
        self.navigationController = navigationController
        self.dependency = dependency
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    func start(with deepLink: DeepLink? = nil) {
        if case let .login(code) = deepLink, let code = code {
            dependency.setAuthCode(code: code)
        }

        let action = LoginViewModelAction(
            showMainScene: showMainTabBar,
            showGithubLoginScene: showGithubLogin,
            showAppleLoginScene: showAppleLogin
        )

        let viewController = dependency.makeLoginViewController(action: action)
        navigationController.viewControllers = [viewController]
    }

    private func showMainTabBar() {
        // new flow start or push view controller
    }

    private func showGithubLogin() {
        guard let url = URL(string: "https://github.com/login/oauth/authorize?client_id=1f7a746c1d01cc6de0bf") else {
            return
        }

        UIApplication.shared.open(url)
    }

    private func showAppleLogin() {}
}
