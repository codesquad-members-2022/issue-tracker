//
//  TokenResponse.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/15.
//

import Foundation


struct TokenResponse: Codable {
    let accessToken, scope, tokenType: String
    
    enum CodingKeys: String, CodingKey {
        case accessToken = "access_token"
        case scope
        case tokenType = "token_type"
    }
}
