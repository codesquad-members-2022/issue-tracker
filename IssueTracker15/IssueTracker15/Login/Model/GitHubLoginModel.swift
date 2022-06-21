//
//  GitHubLoginModel.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/20.
//

import Foundation

struct GitHubLoginModel {
    
    func getLoginURL() -> URL? {
        let baseUrlString = OAuthLoginSource.LoginUrlString.github.rawValue
        let clientID = OAuthLoginSource.ClientID.github.rawValue
        var components = URLComponents(string: baseUrlString)
        
        components?.queryItems = [
            URLQueryItem(name: "client_id", value: clientID)
        ]
        
        guard let url = components?.url else { return nil }
        return url
    }
}
