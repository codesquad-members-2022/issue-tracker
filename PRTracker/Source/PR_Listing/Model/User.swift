//
//  User.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/20.
//

import Foundation

struct User: Codable {
    let id: Int
    let name: String?
    let userName: String?
    let reposURL: String?
    
    enum CodingKeys: String, CodingKey {
        case id, name
        case userName = "login"
        case reposURL = "repos_url"
    }
}
