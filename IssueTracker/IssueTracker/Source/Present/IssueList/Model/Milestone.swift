//
//  Milestone.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/14.
//

import Foundation

struct Milestone: Codable {
    let id: Int
    let title: String
    let description: String?

    enum CodingKeys: String, CodingKey {
        case id
        case title
        case description
    }

}
