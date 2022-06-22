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

        let issueListViewModel = IssueListViewModel()

        issueListViewModel.loadIssueList() // issueList 요청

        // success
        issueListViewModel.loginSuccess = {
            DispatchQueue.main.async {
                let issueListVC = IssueListViewController()
                self.window?.rootViewController = issueListVC
                issueListVC.viewModel = issueListViewModel
            }
        }
        // failure
        issueListViewModel.loginFailure = {
            DispatchQueue.main.async {
                self.window?.rootViewController = LoginViewController()
            }

        }

        window?.makeKeyAndVisible()
    }

    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {

        let loginRepository = LoginRepository()

        guard let response = URLContexts.first?.url,
        let authorizedCode = response.absoluteString.components(separatedBy: "code=").last else { return }

        let urlRequest = IssueTrackerTarget.requestAccessToken(code: authorizedCode)

        loginRepository.getGithubAccessToken(urlRequest) { tokenValue in
            UserDefaults.standard.set(tokenValue, forKey: Environment.token)
        }
    }

}
