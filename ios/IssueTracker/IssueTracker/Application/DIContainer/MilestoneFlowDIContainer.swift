//
//  MilestoneFlowDIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

final class MilestoneFlowDIContainer: DIContainer {
    func makeCoordinator(navigationController: UINavigationController) -> Coordinator {
        MilestoneFlowCoordinator(
            navigationController: navigationController,
            dependencies:
            self
        )
    }
}

extension MilestoneFlowDIContainer: MilestoneFlowCoordinatorDependencies {
    func makeMilestoneViewController() -> UIViewController {
        MilestoneViewController()
    }
}
