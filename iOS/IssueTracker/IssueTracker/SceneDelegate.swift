//
//  SceneDelegate.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/13.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?


    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)
        
        let rootVC = TabBarController()
        self.window?.rootViewController = rootVC
        window?.makeKeyAndVisible()
    }

}

