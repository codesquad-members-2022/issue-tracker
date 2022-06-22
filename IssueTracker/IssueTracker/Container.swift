//
//  Container.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/22.
//

import UIKit

struct Container {
    
    enum Screen {
        case login
        case issue
    }
    
    func getViewController(_ screen: Screen) -> UIViewController {
        switch screen {
        case .login:
            return LoginViewController(service: OAuthService())
        case .issue:
            return UINavigationController(rootViewController: IssueViewController(model: IssueModel(service: GitHubService())))
        }
    }
}
