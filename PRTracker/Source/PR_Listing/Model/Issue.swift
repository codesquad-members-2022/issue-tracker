//
//  Pull.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/15.
//

import Foundation

struct Issue: Codable {
    let id: Int
    let title: String
    let body: String?
    let state: String
    let creator: User?
    let assignees: [User]?
    let labels: [Label]
    let milestone: Milestone?
    let pull: Pull?
    
    enum CodingKeys: String, CodingKey {
        case id
        case title
        case body
        case state
        case creator = "user"
        case assignees
        case pull = "pull_request"
        case labels
        case milestone
    }
}


struct Pull: Codable {
    let url: String
    
    enum CodingKeys: String, CodingKey {
        case url
    }
}

struct Label: Codable {
    let id: Int
    let color: String
    let name: String
}

struct Milestone: Codable {
    let id: Int
    let title: String
}
