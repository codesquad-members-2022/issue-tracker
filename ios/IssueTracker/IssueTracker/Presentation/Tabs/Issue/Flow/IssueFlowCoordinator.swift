//
//  IssueFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

protocol IssueFlowCoordinatorDependencies {
    func makeIssueViewController() -> UIViewController
}

final class IssueFlowCoordinator: Coordinator {
    private let navigationController: UINavigationController
    private let dependencies: IssueFlowCoordinatorDependencies

    init(navigationController: UINavigationController, dependencies: IssueFlowCoordinatorDependencies) {
        self.navigationController = navigationController
        self.dependencies = dependencies
    }

    func start(with _: DeepLink? = nil) {
        let viewController = dependencies.makeIssueViewController()
        navigationController.viewControllers = [viewController]
    }
}
