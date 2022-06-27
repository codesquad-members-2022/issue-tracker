//
//  TokenResource.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

struct TokenResource: APIResource {
    
    private let clientID: String
    private let clientSecret: String
    private let code: String
    
    init(clientID: String, clientSecret: String, code: String) {
        self.clientID = clientID
        self.clientSecret = clientSecret
        self.code = code
    }
    
    typealias ModelType = TokenResponse
    
    var path: String {
        return "/login/oauth/access_token"
    }
    
    var query: [String: String]? {
        var query = [String: String]()
        query["client_id"] = clientID
        query["client_secret"] = clientSecret
        query["code"] = code
        return query
    }
}
