//
//  MilestoneFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

protocol MilestoneFlowCoordinatorDependencies {
    func makeMilestoneViewController() -> UIViewController
}

final class MilestoneFlowCoordinator: Coordinator {
    private let navigationController: UINavigationController
    private let dependencies: MilestoneFlowCoordinatorDependencies

    init(navigationController: UINavigationController, dependencies: MilestoneFlowCoordinatorDependencies) {
        self.navigationController = navigationController
        self.dependencies = dependencies
    }

    func start(with _: DeepLink?) {
        let viewController = dependencies.makeMilestoneViewController()
        navigationController.viewControllers = [viewController]
    }
}
