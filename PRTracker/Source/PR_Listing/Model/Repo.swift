//
//  Repository.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

struct Repo: Codable {
    let id: Int
    let name: String
    let fullName: String
    
    enum CodingKeys: String, CodingKey {
        case id, name
        case fullName = "full_name"
    }
}
