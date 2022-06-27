//
//  AppFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/14.
//

import UIKit

protocol AppFlowCoordinatorDependencies {
    func makeTabBarFlowDIContainer() -> DIContainer
    func makeLoginFlowDIContainer() -> DIContainer
    func checkUserLoggedIn() -> Bool
}

final class AppFlowCoordinator: BaseCoordinator {
    private let navigationController: UINavigationController
    private let appDependencies: AppFlowCoordinatorDependencies

    init(navigationController: UINavigationController, dependencies: AppFlowCoordinatorDependencies) {
        self.navigationController = navigationController
        self.appDependencies = dependencies
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    override func start() {
        if appDependencies.checkUserLoggedIn() {
            print("Run")
            runTabBarFlow()
        } else {
            runLoginFlow()
        }
    }

    override func start(with deepLink: DeepLink?) {
        guard let deepLink = deepLink else {
            runLoginFlow() // or run default flow
            return
        }

        switch deepLink {
        case .home:
            runTabBarFlow(with: deepLink)
            return
        case .login:
            runLoginFlow(with: deepLink)
            return
        @unknown case _:
            // run default flow
            return
        }
    }

    func runLoginFlow(with deepLink: DeepLink? = nil) {
        let container = appDependencies.makeLoginFlowDIContainer()
        let flow = container.makeCoordinator(navigationController: navigationController)

        flow.start(with: deepLink)
        addDependency(flow)
    }

    func runTabBarFlow(with deepLink: DeepLink? = nil) {
        let container = appDependencies.makeTabBarFlowDIContainer()
        let flow = container.makeCoordinator(navigationController: navigationController)

        flow.start(with: deepLink)
        addDependency(flow)
    }
}
