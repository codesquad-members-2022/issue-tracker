//
//  LoginFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/14.
//

import UIKit

protocol LoginFlowCoordinatorDependencies {
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

    func start() {
        let action = LoginViewModelAction(showMainScene: showMainTabBar)
        let viewController = dependency.makeLoginViewController(action: action)
        navigationController.viewControllers = [viewController]
    }

    private func showMainTabBar() {
        // new flow start or push view controller
    }
}
