//
//  SceneDelegate.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    
    var window: UIWindow?
    
    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        if let url = URLContexts.first?.url, url.absoluteString.starts(with: "pr-tracker://") {
            guard let queryString = url.query else { return }
            guard let code = queryString.components(separatedBy: "=").last else { return }
            
            GitHubLoginManager.shared.getAccessToken(with: code) { [weak self] granted in
                guard let alert = self?.makeAlert(granted: granted) else { return }
                DispatchQueue.main.async {
                    self?.window?.rootViewController?.present(alert, animated: true)
                }
            }
        }
    }
    
    private func makeAlert(granted: Bool) -> UIAlertController {
        if !granted {
            let alert = UIAlertController(title: "Github 로그인에 실패했습니다.", message: "Github 로그인에 실패했습니다. 다시 시도해주세요.", preferredStyle: .alert)
            let goToLogin = UIAlertAction(title: "확인", style: .default) { _ in
                self.window?.rootViewController?.performSegue(withIdentifier: "loginSegue", sender: nil)
            }
            alert.addAction(goToLogin)
            return alert
        } else {
            let alert = UIAlertController(title: "Github 로그인에 성공했습니다.", message: "", preferredStyle: .alert)
            let goToMain = UIAlertAction(title: "확인", style: .default) { _ in
                self.window?.rootViewController?.performSegue(withIdentifier: "mainSegue", sender: nil)
            }
            alert.addAction(goToMain)
            return alert
        }
    }
    
    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let _ = (scene as? UIWindowScene) else { return }
    }
}
