//
//  AccountFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

protocol AccountFlowCoordinatorDependencies {
    func makeAccountViewController() -> UIViewController
}

final class AccountFlowCoordinator: Coordinator {
    private let navigationController: UINavigationController
    private let dependencies: AccountFlowCoordinatorDependencies

    init(navigationController: UINavigationController, dependencies: AccountFlowCoordinatorDependencies) {
        self.navigationController = navigationController
        self.dependencies = dependencies
    }

    func start(with _: DeepLink?) {
        let viewController = dependencies.makeAccountViewController()
        navigationController.viewControllers = [viewController]
    }
}
