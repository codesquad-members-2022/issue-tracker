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
            if codeURL.absoluteString.starts(with: "issuetrackerapp://") {
                if let code = codeURL.absoluteString.split(separator: "=").last.map({String($0)}) {
                    SignInManager.shared.requestAccessToken(code: code) { result in
                        switch result {
                        case let .success(data):
                            // 서버로부터 JWT토큰 요청하는 로직 필요
                            return
                        case let .failure(error):
                            // 다시 로그인을 요청하도록 하는 Noti 로직 필요
                            return
                        }
                    }
                }
            }
        }
    }
}
