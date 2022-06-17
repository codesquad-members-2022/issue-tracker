//
//  AppFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/14.
//

import UIKit

protocol Coordinator: AnyObject {
    func start()
}

protocol AppFlowCoordinatorDependencies {
    func makeLoginFlowDIContainer() -> DIContainer
}

final class AppFlowCoordinator: Coordinator {
    private let navigationController: UINavigationController
    private let appDIContainer: AppFlowCoordinatorDependencies

    init(navigationController: UINavigationController, dependency: AppFlowCoordinatorDependencies) {
        self.navigationController = navigationController
        self.appDIContainer = dependency
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    func start() {
        runLoginFlow()
    }

    func runLoginFlow() {
        let container = appDIContainer.makeLoginFlowDIContainer()
        let flow = container.makeCoordinator(navigationController: navigationController)
        flow.start()
    }
}
