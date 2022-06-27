//
//  Container.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/22.
//

import UIKit

class Container {
    
    static let shared = Container()
    
    private let accessToken: String? = GithubUserDefaults.getToken()
    
    enum Screen {
        case login
        case issue
        case newIssue
        case optionSelect
    }
    
    func setAccessToken(_ token: String) {
        accessToken = token
        GithubUserDefaults.setToken(token)
    }
    
    func buildRootViewController() -> UIViewController {
        if let accessToken = GithubUserDefaults.getToken() {
            return buildViewController(.issue)
        } else {
            return buildViewController(.login)
        }
    }
    
    private func buildViewController(_ screen: Screen) -> UIViewController {
    // TODO: AccessToken이 필요한 뷰컨과 필요하지 않은 뷰컨을 구분해서 + 옵셔널도 벗겨서 넣어줘야 함. 그런데 UIViewController는 옵셔널이면 안됨
        switch screen {
        case .login: // 토큰 x
            return LoginViewController(service: OAuthService())
        case .issue: // 토큰 o
            let service = IssueService()
            let model = IssueModel(service: service, token: accessToken)
            let viewController = IssueViewController(model: model)
            return UINavigationController(rootViewController: viewController)
        case .newIssue: // 토큰 x
            return NewIssueViewController()
        case .optionSelect: // 토큰 o
            return OptionSelectViewController(token: accessToken) // token
        }
    }
    
    func buildViewControllerWithToken(_ screen: Screen) -> UIViewController? {
        guard let token = accessToken else {
            return nil
        }
        return buildViewController(screen)
    }
}
