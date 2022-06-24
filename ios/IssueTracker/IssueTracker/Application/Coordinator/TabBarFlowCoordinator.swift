//
//  TabBarFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/23.
//

import UIKit

protocol TabBarFlowCoordinatorDependencies {
    func makeIssueFlowDIContainer() -> DIContainer
    func makeLabelFlowDIContainer() -> DIContainer
    func makeMilestoneFlowDIContainer() -> DIContainer
    func makeAccountFlowDIContainer() -> DIContainer
}

class TabBarFlowCoordinator: Coordinator {
    private let navigationController: UINavigationController
    private let dependency: TabBarFlowCoordinatorDependencies

    init(navigationController: UINavigationController, dependency: TabBarFlowCoordinatorDependencies) {
        self.navigationController = navigationController
        self.dependency = dependency
    }

    func start(with _: DeepLink?) {
        let tabBarController = UITabBarController()
        let issueNavigationController = UINavigationController()
        let labelNavigationController = UINavigationController()
        let milestoneNavigationController = UINavigationController()
        let accountNavigationController = UINavigationController()

        tabBarController.viewControllers = [
            issueNavigationController,
            labelNavigationController,
            milestoneNavigationController,
            accountNavigationController
        ]

        let issueFlowDIContainer = dependency.makeIssueFlowDIContainer()
        let labelFlowDIContainer = dependency.makeLabelFlowDIContainer()
        let milestoneFlowDIContainer = dependency.makeMilestoneFlowDIContainer()
        let accountFlowDIContainer = dependency.makeAccountFlowDIContainer()

        let issueFlowCoordinator = issueFlowDIContainer.makeCoordinator(navigationController: issueNavigationController)

        let labelFlowCoordinator = labelFlowDIContainer.makeCoordinator(navigationController: labelNavigationController)

        let milestoneFlowCoordinator = milestoneFlowDIContainer.makeCoordinator(navigationController: milestoneNavigationController)

        let accountFlowCoordinator = accountFlowDIContainer.makeCoordinator(navigationController: accountNavigationController)

        issueFlowCoordinator.start(with: nil)
        labelFlowCoordinator.start(with: nil)
        milestoneFlowCoordinator.start(with: nil)
        accountFlowCoordinator.start(with: nil)
    }
}
