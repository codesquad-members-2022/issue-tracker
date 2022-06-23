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
        case issue(token: String)
        case newIssue
    }
    
    func getViewController(_ screen: Screen) -> UIViewController {
        switch screen {
        case .login:
            return LoginViewController(service: OAuthService())
        case .issue(let token):
            return UINavigationController(rootViewController: IssueViewController(model: IssueModel(service: IssueService(), token: token)))
        case .newIssue:
            return NewIssueViewController()
        }
    }
}
