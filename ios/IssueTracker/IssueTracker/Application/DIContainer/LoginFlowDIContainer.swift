//
//  LoginFlowDIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/17.
//

import UIKit

class LoginFlowDIContainer: DIContainer {
    private let localStorage = UserDefaults.standard

    func makeLoginViewController(action: LoginViewModelAction) -> UIViewController {
        let viewModel = DefaultLoginViewModel(navigationAction: action)
        return LoginViewController.create(with: viewModel)
    }

    func makeCoordinator(navigationController: UINavigationController) -> Coordinator & LoginFlowCoordinatorOutput {
        LoginFlowCoordinator(navigationController: navigationController, dependency: self)
    }
}

extension LoginFlowDIContainer: LoginFlowCoordinatorDependencies {
    func setAuthCode(code: String) {
        localStorage.set(code, forKey: LocalStorageConstants.AuthCode)
    }
}
