//
//  SceneDelegate.swift
//  IssueTracker15_1
//
//  Created by 박진섭 on 2022/06/13.
//

import UIKit
import Alamofire
import OSLog

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    private let loginVC = LoginViewController()
    private var accessTokenManager = AccessTokenManager()
    
    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        // open url을 통해 로그인창이 뜬다면, Code를 받아옴.
        let code = URLContexts.first?.url.query?.replacingOccurrences(of: "code=", with: "")
        
        accessTokenManager.requestAccessToken(code: code) { [weak self] result in
            switch result {
            case let result as AccessInfo:
                UserDefaults.standard.set(result.accessToken, forKey: "Access_Token")
                self?.loginVC.presentIssueList()
            case let result as Error:
                os_log(.error, "\(result.localizedDescription)")
            default:
                os_log(.error, "Unexpected Error occured while getting a access Token")
            }
        }
    }
    
    
    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)
        let rootViewController = UINavigationController(rootViewController: loginVC)
        rootViewController.view.backgroundColor = .secondarySystemBackground
        window?.rootViewController = rootViewController
        window?.makeKeyAndVisible()
    }
}
