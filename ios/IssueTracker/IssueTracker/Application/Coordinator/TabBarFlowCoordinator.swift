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

final class TabBarFlowCoordinator: Coordinator {
    private let navigationController: UINavigationController
    private let dependencies: TabBarFlowCoordinatorDependencies

    init(navigationController: UINavigationController, dependencies: TabBarFlowCoordinatorDependencies) {
        self.navigationController = navigationController
        self.dependencies = dependencies
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

        issueNavigationController.tabBarItem = .init(
            title: "Issue",
            image: .info,
            selectedImage: .info
        )

        labelNavigationController.tabBarItem = .init(
            title: "Label",
            image: .tag,
            selectedImage: .tag
        )

        milestoneNavigationController.tabBarItem = .init(
            title: "Milestone",
            image: .signpost,
            selectedImage: .signpost
        )

        accountNavigationController.tabBarItem = .init(
            title: "Account",
            image: .profile,
            selectedImage: .profile
        )

        let issueFlowDIContainer = dependencies.makeIssueFlowDIContainer()
        let labelFlowDIContainer = dependencies.makeLabelFlowDIContainer()
        let milestoneFlowDIContainer = dependencies.makeMilestoneFlowDIContainer()
        let accountFlowDIContainer = dependencies.makeAccountFlowDIContainer()

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
