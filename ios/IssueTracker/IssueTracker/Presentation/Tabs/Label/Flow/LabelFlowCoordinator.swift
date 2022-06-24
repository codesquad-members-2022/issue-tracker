//
//  LabelFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

protocol LabelFlowCoordinatorDependencies {
    func makeLabelViewController() -> UIViewController
}

final class LabelFlowCoordinator: Coordinator {
    private let navigationController: UINavigationController
    private let dependencies: LabelFlowCoordinatorDependencies

    init(navigationController: UINavigationController, dependencies: LabelFlowCoordinatorDependencies) {
        self.navigationController = navigationController
        self.dependencies = dependencies
    }

    func start(with _: DeepLink? = nil) {
        let viewController = dependencies.makeLabelViewController()
        navigationController.viewControllers = [viewController]
    }
}
