//
//  SceneDelegate.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/14.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?
    var appCoordinator: AppFlowCoordinator?

    func scene(_ scene: UIScene, willConnectTo _: UISceneSession, options _: UIScene.ConnectionOptions) {
        guard let scene = (scene as? UIWindowScene) else {
            return
        }

        let navigationController = UINavigationController()
        let appContainer = AppFlowDIContainer()

        appCoordinator = AppFlowCoordinator(navigationController: navigationController, dependency: appContainer)

        appCoordinator?.start()

        window = UIWindow(windowScene: scene)
        window?.rootViewController = navigationController
        window?.makeKeyAndVisible()
    }

    // MARK: - URL Scheme
    func scene(_: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        guard let url = URLContexts.first?.url else {
            return
        }

        let deepLink = DeepLink.build(with: url)
        appCoordinator?.start(with: deepLink)
    }

    // MARK: - Universal Links
    func scene(_: UIScene, continue userActivity: NSUserActivity) {
        guard userActivity.activityType == NSUserActivityTypeBrowsingWeb, let incomingURL = userActivity.webpageURL else {
            return
        }

        let deepLink = DeepLink.build(with: incomingURL)
        appCoordinator?.start(with: deepLink)
    }
}
