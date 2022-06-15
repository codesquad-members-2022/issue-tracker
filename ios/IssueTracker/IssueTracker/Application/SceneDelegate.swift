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

        appCoordinator = AppFlowCoordinator(navigationController: navigationController)

        appCoordinator?.start()

        window = UIWindow(windowScene: scene)
        window?.rootViewController = navigationController
        window?.makeKeyAndVisible()
    }
}
