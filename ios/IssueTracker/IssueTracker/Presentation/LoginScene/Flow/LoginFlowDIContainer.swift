//
//  LoginFlowDIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/17.
//

import UIKit

protocol DIContainer {
    func makeCoordinator(navigationController: UINavigationController) -> Coordinator
}

class LoginFlowDIContainer: DIContainer {
    func makeLoginViewController(action: LoginViewModelAction) -> UIViewController {
        let viewModel = DefaultLoginViewModel(navigationAction: action)
        return LoginViewController.create(with: viewModel)
    }

    func makeCoordinator(navigationController: UINavigationController) -> Coordinator {
        LoginFlowCoordinator(navigationController: navigationController, dependency: self)
    }
}

extension LoginFlowDIContainer: LoginFlowCoordinatorDependencies {}
