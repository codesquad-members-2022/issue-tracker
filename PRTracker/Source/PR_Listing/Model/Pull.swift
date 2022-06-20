//
//  Pull.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/15.
//

import Foundation

struct Pull: Codable {
    let id: Int
    let title: String
    let body: String?
    let state: String
    let creator: User?
    let assignees: [User]?
    let labels: [Label]
    let milestone: Milestone?
    
    enum CodingKeys: String, CodingKey {
        case id
        case title
        case body
        case state
        case creator = "user"
        case assignees
        case labels
        case milestone
    }
}

struct User: Codable {
    let id: Int
    let name: String?
    let reposURL: String?
    
    enum CodingKeys: String, CodingKey {
        case id, name
        case reposURL = "repos_url"
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
