//
//  IssueFlowDIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

final class IssueFlowDIContainer: DIContainer {
    func makeCoordinator(navigationController: UINavigationController) -> Coordinator {
        IssueFlowCoordinator(navigationController: navigationController, dependencies: self)
    }
}

extension IssueFlowDIContainer: IssueFlowCoordinatorDependencies {
    func makeIssueViewController() -> UIViewController {
        UIViewController()
    }
}
