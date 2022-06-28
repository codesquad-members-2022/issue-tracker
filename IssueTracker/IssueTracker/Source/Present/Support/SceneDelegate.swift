//
//  SceneDelegate.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/13.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?
    var rootWindow: RootWindow!

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        self.rootWindow = RootWindow(scene: windowScene)

        window = self.rootWindow
        window?.makeKeyAndVisible()
    }

    // MARK: - 토큰 왔을 때 처리하는 메서드
    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {

        let loginViewModel = LoginViewModel(repository: LoginRepository())
        guard let response = URLContexts.first?.url,
              let authorizedCode = response.absoluteString.components(separatedBy: "code=").last else { return }

        let urlRequest = IssueTrackerTarget.requestAccessToken(code: authorizedCode)

        loginViewModel.repository.getGithubAccessToken(urlRequest) { tokenValue in
            UserDefaults.standard.set(tokenValue, forKey: Environment.token)
            self.rootWindow?.setIssueListVC()
        }

    }

}
