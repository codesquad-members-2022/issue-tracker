//
//  TokenValidationResponse.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/22.
//

import Foundation

struct TokenValidatationResponse: Codable {
    let accessToken: String
    let scopes: [String]
    let expiresAt: Date
    
    enum CodingKeys: String, CodingKey {
        case accessToken = "token"
        case scopes
        case expiresAt = "expires_at"
    }
}
