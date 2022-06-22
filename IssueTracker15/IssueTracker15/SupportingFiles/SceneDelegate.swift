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
    
    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        // open url을 통해 로그인창이 뜬다면, Code를 받아옴.
        let code = URLContexts.first?.url.query?.replacingOccurrences(of: "code=", with: "")
        let postURL = Bundle.main.gitLoginAccessTokenURL
        
        // code와 함꼐 parameter를 Post
        // 추후 로직 분리 예정.
        let parameters = [OAuthLoginSourceKey.GitHub.Query.clientID.rawValue : Bundle.main.gitClientID,
                          OAuthLoginSourceKey.GitHub.Query.clientSecret.rawValue: Bundle.main.gitClientSecret,
                          OAuthLoginSourceKey.GitHub.Query.code.rawValue : code]

        let headers: HTTPHeaders = ["Accept":"application/json"]
        
        
        
        AF
            .request(postURL, method: .post, parameters: parameters, headers: headers)
            .responseDecodable { [weak self] (response: DataResponse<AccessInfo, AFError>) in
                switch response.result {
                    // 성공시 화면이 넘어간다.
                case let .success(accessInfo):
                    // UserDefault에 저장
                    UserDefaults.standard.set(accessInfo.accessToken, forKey: "accessToken")
                    self?.loginVC.presentIssueList(accessToken: accessInfo.accessToken)
                    
                case let .failure(error):
                    os_log(.error, "\(error.localizedDescription)")
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

