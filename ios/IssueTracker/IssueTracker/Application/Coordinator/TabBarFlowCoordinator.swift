//
//  TabBarFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/23.
//

import UIKit

protocol TabBarFlowCoordinatorDependencies {
    func makeIssueFlowDIContainer() -> IssueFlowDIContainer
    func makeLabelFlowDIContainer() -> LabelFlowDIContainer
    func makeMilestoneFlowDIContainer() -> MilestoneFlowDIContainer
    func makeAccountFlowDIContainer() -> AccountFlowDIContainer
}

final class TabBarFlowCoordinator: BaseCoordinator {
    private let navigationController: UINavigationController
    private let dependencies: TabBarFlowCoordinatorDependencies

    init(navigationController: UINavigationController, dependencies: TabBarFlowCoordinatorDependencies) {
        self.navigationController = navigationController
        self.dependencies = dependencies
    }

    override func start(with _: DeepLink?) {
        let tabBarController = UITabBarController()
        let issueNavigationController = UINavigationController()
        let labelNavigationController = UINavigationController()
        let milestoneNavigationController = UINavigationController()
        let accountNavigationController = UINavigationController()

        tabBarController.tabBar.addTopBorder(with: .gray, andWidth: 0.5)

        navigationController.viewControllers = [tabBarController]
        navigationController.navigationBar.isHidden = true

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

        tabBarController.viewControllers = [
            issueNavigationController,
            labelNavigationController,
            milestoneNavigationController,
            accountNavigationController
        ]

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

        addDependency(issueFlowCoordinator)
        addDependency(labelFlowCoordinator)
        addDependency(milestoneFlowCoordinator)
        addDependency(accountFlowCoordinator)
    }
}
