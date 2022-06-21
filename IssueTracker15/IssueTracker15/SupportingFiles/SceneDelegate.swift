//
//  SceneDelegate.swift
//  IssueTracker15_1
//
//  Created by 박진섭 on 2022/06/13.
//

import UIKit
import Alamofire

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        // open url을 통해 로그인창이 뜬다면, Code를 받아옴.
        let code = URLContexts.first?.url.query?.replacingOccurrences(of: "code=", with: "")
        
        let postURL = OAuthLoginSource.AssessTokenURLString.github.rawValue
        let parameters = ["client_id": OAuthLoginSource.ClientID.github.rawValue,
                          "client_secret": OAuthLoginSource.ClientSecret.github.rawValue,
                          "code": code]

        let headers: HTTPHeaders = ["Accept":"application/json"]
        
        AF
            .request(postURL, method: .post, parameters: parameters, headers: headers)
            .responseDecodable { (response: DataResponse<AccessInfo, AFError>) in
                switch response.result {
                case let .success(data):
                    print(data)
                case let .failure(error):
                    print(error)
            }
        }
    }
    
    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)
        let rootViewController =  LoginViewController()
        rootViewController.view.backgroundColor = .secondarySystemBackground
        window?.rootViewController = rootViewController
        window?.makeKeyAndVisible()
    }
}

