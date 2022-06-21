//
//  OAuthLoginSource.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/20.
//

struct OAuthLoginSource {
    enum LoginUrlString: String {
        case github = "https://github.com/login/oauth/authorize"
        case apple
    }
    
    enum AssessTokenURLString: String {
        case github = "https://github.com/login/oauth/access_token"
        case apple
    }
    
    enum ClientID: String {
        case github = "Iv1.1cb3e4aa4c245fe8"
        case apple
    }
    
    enum ClientSecret: String {
        case github = ""
        case apple
    }
}
