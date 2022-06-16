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

    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        if let codeURL = URLContexts.first?.url {
            SignInManager.shared.requestAccessToken(codeURL: codeURL) { result in
                switch result {
                case let .success(data):
                    // 서버로부터 JWT토큰 요청하는 로직 필요
                    return
                case let .failure(error):
                    NotificationCenter.default.post(name: NotificationNames.didGetSignInError, object: error)
                    return print(error)
                }
            }
        }
    }
}

// MARK: - enum / static properties
extension SceneDelegate {
    enum NotificationNames {
        static let didGetSignInError = Notification.Name("SceneDelegateDidGetSignInError")
    }
}
