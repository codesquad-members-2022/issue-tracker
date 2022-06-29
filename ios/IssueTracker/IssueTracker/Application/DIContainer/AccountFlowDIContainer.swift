//
//  AccountFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

final class AccountFlowDIContainer {
    func makeCoordinator(navigationController: UINavigationController) -> Coordinator {
        AccountFlowCoordinator(navigationController: navigationController, dependencies: self)
    }
}

extension AccountFlowDIContainer: AccountFlowCoordinatorDependencies {
    func makeAccountViewController() -> UIViewController {
        AccountViewController()
    }
}
