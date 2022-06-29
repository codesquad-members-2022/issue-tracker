//
//  UserResource.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

struct GetUser: APIEndpoint {
    typealias ModelType = User
    
    let userName: String? = nil
    
    var httpMethod: HTTPMethod {
        return .get
    }
    
    var path: String {
        if let userName = userName {
            return "/users/\(userName)"
        }
        return "/user"
    }
    
    var query: [String: String]? {
        return nil
    }
}
