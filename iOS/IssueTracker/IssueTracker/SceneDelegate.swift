//
//  SceneDelegate.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/13.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?
    private let signInManager = SignInManager()


    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)
        
        let rootVC = SignInViewController()
        self.window?.rootViewController = rootVC
        window?.makeKeyAndVisible()
    }

    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        if let codeURL = URLContexts.first?.url {
            if codeURL.absoluteString.starts(with: "issuetrackerapp://") {
                if let code = codeURL.absoluteString.split(separator: "=").last.map({String($0)}) {
                    signInManager.requestAccessToken(with: code)
                }
            }
        }
    }

}

