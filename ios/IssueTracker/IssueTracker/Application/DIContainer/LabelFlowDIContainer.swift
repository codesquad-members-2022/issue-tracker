//
//  LabelFlowDIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

final class LabelFlowDIContainer {
    func makeCoordinator(navigationController: UINavigationController) -> Coordinator {
        LabelFlowCoordinator(navigationController: navigationController, dependencies: self)
    }
}

extension LabelFlowDIContainer: LabelFlowCoordinatorDependencies {
    func makeLabelViewController() -> UIViewController {
        LabelViewController()
    }
}
