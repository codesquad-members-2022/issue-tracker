//
//  LoginManager.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/15.
//

import Foundation
import UIKit

class LoginManager {
    
    static func requestGithubAuthorize() {
        let clientID = "e6386d0321b6dc2820c0"
        let scope = "repo user"
        let urlString = "https://github.com/login/oauth/authorize"
        guard var components = URLComponents(string: urlString) else { return }
        components.queryItems = [
            URLQueryItem(name: "client_id", value: clientID),
            URLQueryItem(name: "scope", value: scope)
        ]
        print(components.url!)

        guard let url = URL(string: "\(components.url!)"), UIApplication.shared.canOpenURL(url) else { return }

        UIApplication.shared.open(url)
    }
}
