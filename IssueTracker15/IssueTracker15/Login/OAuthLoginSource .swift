//
//  OAuthLoginSource.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/20.
//

// APIPropertyList의 Key값.
struct OAuthLoginSourceKey {
    
    enum GitHub: String {
        case loginUrlString = "Git_Login_URL"
        case accessTokenURLString = "Git_Login_Access_Token_URL"
        case clientID = "Git_Client_ID"
        case clientSecret = "Git_Client_Secret_key"
        
        enum Query: String {
            case clientID = "client_id"
            case clientSecret = "client_secret"
            case code = "code"
        }
    }
    
    enum Apple: String {
        case loginUrlString
        case accessTokenURLString
        case clientID
        case clientSecret
    }
}
