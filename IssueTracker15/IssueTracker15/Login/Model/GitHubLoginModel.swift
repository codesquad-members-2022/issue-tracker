//
//  GitHubLoginModel.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/20.
//

import Foundation

struct GitHubLoginModel {
    
    func getLoginURL() -> URL? {
        let baseUrlString = Bundle.main.gitLoginURL
        let clientID = Bundle.main.gitClientID
        var components = URLComponents(string: baseUrlString)
        
        components?.queryItems = [
            URLQueryItem(name: OAuthLoginSourceKey.GitHub.Query.clientID.rawValue,
                         value: clientID)
        ]
        
        guard let url = components?.url else { return nil }
        return url
    }
}
