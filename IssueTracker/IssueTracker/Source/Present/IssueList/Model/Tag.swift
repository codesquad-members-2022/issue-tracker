//
//  Tag.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/14.
//

import Foundation

struct Tag: Codable {
    let id: Int
    let title: String
    let description: String?
    let backgroundColor: String

    enum CodingKeys: String, CodingKey {
        case id
        case title = "name"
        case description = "body"
        case backgroundColor = "color"
    }
}
