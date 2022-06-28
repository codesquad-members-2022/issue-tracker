//
//  Repo.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/28.
//

import Foundation

struct Repository: Codable {
    let id: Int
    let name: String
    let fullName: String
    
    enum CodingKeys: String, CodingKey {
        case id
        case name
        case fullName = "full_name"
    }
}
