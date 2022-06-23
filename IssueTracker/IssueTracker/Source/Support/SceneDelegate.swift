//
//  SceneDelegate.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/13.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?
    var issueListViewModel: IssueListViewModel?
    var rootWindow: RootWindow!

    // TODO: -SceneDelegate 가 많은 일을 하는 거같다.
    /// 로그인 로직(토큰의 유효성 처리)이 IssueListViewModel 에 있는 건데 이건 좀 아닌 것 같다.
    // TODO: - IssueListVC를 TabBarVC와 SceneDelegate 두군데에서 처리하는데 한 곳으로 뺼 로직 고민 --> Splash View
    // TODO: - SceneDelegate 에서 왜 IssueListVM을 가지고 있나요? --> SceneDelegateViewModel
    // TODO: - 로그인 흐름 하면서 
    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        self.rootWindow = RootWindow(scene: windowScene)
        self.rootWindow?.setRootViewController()
        window = self.rootWindow
        window?.makeKeyAndVisible()
    }

    // MARK: - 토큰 왔을 때 처리하는 메서드
    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {

        let loginViewModel = LoginViewModel(repository: LoginRepository())

        guard let response = URLContexts.first?.url,
              let authorizedCode = response.absoluteString.components(separatedBy: "code=").last else { return }

        let urlRequest = IssueTrackerTarget.requestAccessToken(code: authorizedCode)
        // RootVM 한테 요청
        let issueListViewModel = IssueListViewModel()

        issueListViewModel.loadedIssues = {
            DispatchQueue.main.async {
                let issueListVC = IssueListViewController()
                issueListVC.viewModel = issueListViewModel
                self.window?.rootViewController = issueListVC
            }
        }

        loginViewModel.repository.getGithubAccessToken(urlRequest) { tokenValue in
            UserDefaults.standard.set(tokenValue, forKey: Environment.token)
            issueListViewModel.loadIssueList()
            self.rootWindow?.setIssueListVC()
        }

    }

}
