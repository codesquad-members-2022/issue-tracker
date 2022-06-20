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
        
        let rootVC = selectRootViewController()
        self.window?.rootViewController = rootVC
        window?.makeKeyAndVisible()
    }

    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        if let codeURL = URLContexts.first?.url {
            signInManager.requestAccessToken(codeURL: codeURL) { result in
                switch result {
                case let .success(data):
                    self.requestJWTToken(with: data["access_token"] ?? "")
                case let .failure(error):
                    NotificationCenter.default.post(name: NotificationNames.didGetSignInError, object: error)
                }
            }
        }
    }
}

// MARK: - enum / static properties
extension SceneDelegate {
    enum NotificationNames {
        static let didGetSignInError = Notification.Name("SceneDelegateDidGetSignInError")
        static let didSignIn = Notification.Name("SceneDelegateDidSignIn")
    }
}

// MARK: - Private Method
private extension SceneDelegate {
    // API 구현전이라, 임시적으로 만든 JWT 토큰 요청 로직
    func requestJWTToken(with accessToken: String) {
        let url = URL(string: "http://example.com")!
        let data = "a1b2c3d4".data(using: .utf8)
        let response = HTTPURLResponse(url: url, statusCode: 200, httpVersion: nil, headerFields: nil)

        let dummy = DummyData(data: data, response: response, error: nil)

        let stubURLSession = StubURLSession(dummy: dummy)

        stubURLSession.dataTask(with: url) { (data, _, _) in
            if let data = data,
               let jwtToken = String(data: data, encoding: String.Encoding.utf8) {
                UserDefaultManager.saveJWTToken(jwtToken)
                NotificationCenter.default.post(name: NotificationNames.didSignIn, object: self)
            }
        }.resume()
    }

    func selectRootViewController() -> UIViewController {
        return UserDefaultManager.isSavedJWTToken() ?
        TabBarController() : SignInViewController(viewModel: SignInViewModel())
    }
}
