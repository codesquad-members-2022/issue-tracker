//
//  AppFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/14.
//

import UIKit

protocol AppFlowCoordinatorDependencies {
    func makeLoginFlowDIContainer() -> DIContainer
    func checkUserLoggedIn() -> Bool
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

    func start(with deepLink: DeepLink? = nil) {
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
        let container = appDIContainer.makeLoginFlowDIContainer()
        let flow = container.makeCoordinator(navigationController: navigationController)
        flow.start(with: deepLink)
    }

    func runTabBarFlow(with _: DeepLink? = nil) {}
}
