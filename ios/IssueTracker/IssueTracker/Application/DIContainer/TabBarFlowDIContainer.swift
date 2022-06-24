//
//  TabBarFlowDIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

final class TabBarFlowDIContainer: DIContainer {
    func makeCoordinator(navigationController: UINavigationController) -> Coordinator {
        TabBarFlowCoordinator(navigationController: navigationController, dependencies: self)
    }
}

extension TabBarFlowDIContainer: TabBarFlowCoordinatorDependencies {
    func makeIssueFlowDIContainer() -> DIContainer {
        IssueFlowDIContainer()
    }

    func makeLabelFlowDIContainer() -> DIContainer {
        LabelFlowDIContainer()
    }

    func makeMilestoneFlowDIContainer() -> DIContainer {
        MilestoneFlowDIContainer()
    }

    func makeAccountFlowDIContainer() -> DIContainer {
        AccountFlowDIContainer()
    }
}
