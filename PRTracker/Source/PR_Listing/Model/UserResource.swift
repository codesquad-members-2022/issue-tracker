//
//  UserResource.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

struct UserResource: APIResource {
    typealias ModelType = User
    
    let userName: String? = nil
    
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
