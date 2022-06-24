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
    private let navigation = UINavigationController()

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)
        
        let startViewController = selectRootViewController()
        self.window?.rootViewController = startViewController
        window?.makeKeyAndVisible()
    }

    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        if let codeURL = URLContexts.first?.url {
            signInManager.requestJWTToken(codeURL: codeURL) { [weak self] result in
                switch result {
                case let .success(jwtToken):
                    self?.saveJWTToken(jwtToken: jwtToken["JWT_access_token"] ?? "")
                    DispatchQueue.main.async {
                        let rootViewController = TabBarController()
                        self?.window?.rootViewController = rootViewController
                    }
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
    func selectRootViewController() -> UIViewController {
        return UserDefaultManager.isSavedJWTToken() ?
        TabBarController() : makeSignInViewController()
    }

    func saveJWTToken(jwtToken: String) {
        UserDefaultManager.saveJWTToken(jwtToken)
    }

    func makeSignInViewController() -> UIViewController {
        return SignInViewController(
            viewModel: SignInViewModel(useCase: SignInManager()))
    }
}
