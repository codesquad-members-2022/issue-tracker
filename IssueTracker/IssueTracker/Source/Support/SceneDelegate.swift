//
//  SceneDelegate.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/13.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)

        // TODO: Access token 유효성 검사 후 화면 흐름 제어하기

        window?.rootViewController = LoginViewController()
        window?.makeKeyAndVisible()
    }

    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {

        let loginRepository = LoginRepository()

        guard let response = URLContexts.first?.url,
        let authorizedCode = response.absoluteString.components(separatedBy: "code=").last else { return }

        let target = IssueTrackerTarget.requestAccessToken(code: authorizedCode)

        loginRepository.getGithubAccessCode(target) { token in
            UserDefaults.standard.set("access_token", forKey: token)
            DispatchQueue.main.async {
                self.window?.rootViewController = TabBarViewController()
            }
        }

    }

}
