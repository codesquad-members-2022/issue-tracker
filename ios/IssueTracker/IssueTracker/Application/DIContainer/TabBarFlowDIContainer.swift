//
//  TabBarFlowDIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

final class TabBarFlowDIContainer {
    func makeCoordinator(navigationController: UINavigationController) -> Coordinator {
        TabBarFlowCoordinator(navigationController: navigationController, dependencies: self)
    }
}

extension TabBarFlowDIContainer: TabBarFlowCoordinatorDependencies {
    func makeIssueFlowDIContainer() -> IssueFlowDIContainer {
        IssueFlowDIContainer()
    }

    func makeLabelFlowDIContainer() -> LabelFlowDIContainer {
        LabelFlowDIContainer()
    }

    func makeMilestoneFlowDIContainer() -> MilestoneFlowDIContainer {
        MilestoneFlowDIContainer()
    }

    func makeAccountFlowDIContainer() -> AccountFlowDIContainer {
        AccountFlowDIContainer()
    }
}
