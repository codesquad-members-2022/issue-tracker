//
//  SceneDelegate.swift
//  IssueTracker
//
//  Created by juntaek.oh on 2022/06/13.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        let window = UIWindow(windowScene: windowScene)
        window.rootViewController = LoginViewController()
        window.makeKeyAndVisible()
        self.window = window
    }
}
