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
        window?.rootViewController = LoginViewController(
            viewModel: LoginViewModel(repository: LoginRepository())
        )

        window?.makeKeyAndVisible()
    }

    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {

        let loginRepository = LoginRepository()

        guard let response = URLContexts.first?.url,
        let authorizedCode = response.absoluteString.components(separatedBy: "code=").last else { return }

        let urlRequest = IssueTrackerTarget.requestAccessToken(code: authorizedCode)

        loginRepository.getGithubAccessToken(urlRequest) { tokenValue in
            UserDefaults.standard.set(tokenValue, forKey: Environment.token)
            DispatchQueue.main.async {
                self.window?.rootViewController = TabBarViewController()
            }
        }
    }

}
