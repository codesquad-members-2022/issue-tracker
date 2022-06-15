//
//  LoginFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/14.
//

import UIKit

final class LoginFlowCoordinator: Coordinator {
    private let navigationController: UINavigationController

    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    func start() {
        let viewController = LoginViewController()
        navigationController.viewControllers = [viewController]
    }
}
