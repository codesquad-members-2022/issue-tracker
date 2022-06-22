//
//  Bundle.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/21.
//

import Foundation

extension Bundle {

    var gitClientID: String {
        findPropertyWithKey(OAuthLoginSourceKey.GitHub.clientID.rawValue)
    }
    
    var gitClientSecret: String {
        findPropertyWithKey(OAuthLoginSourceKey.GitHub.clientSecret.rawValue)
    }
    
    var gitLoginURL: String {
        findPropertyWithKey(OAuthLoginSourceKey.GitHub.loginUrlString.rawValue)
    }
    
    var gitLoginAccessTokenURL: String {
        findPropertyWithKey(OAuthLoginSourceKey.GitHub.accessTokenURLString.rawValue)
    }
    
    private func findPropertyWithKey(_ key: String) -> String {
        guard let url = Bundle.main.url(forResource: "APIPropertyList", withExtension: "plist"),
              let plistData = try? Data(contentsOf: url),
              let plistDictionary = try? PropertyListSerialization.propertyList(from: plistData,
                                                                                format: nil) as? [String: String],
              let property = plistDictionary[key] else { return "" }
        
        return property
    }
    
}
