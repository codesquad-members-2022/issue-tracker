//
//  TabBarFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/23.
//

import UIKit

protocol TabBarFlowCoordinatorDependencies {
    func makeIssueTabFlowDIContainer() -> DIContainer
    func makeLabelTabFlowDIContainer() -> DIContainer
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

    func start(with _: DeepLink?) {}
}
