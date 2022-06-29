//
//  Issue.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/14.
//

import Foundation

struct Issue: Codable {
    enum State: String, Codable {
        case open
        case written
        case assign
        case comment
        case closed
    }

    let id: Int
    let title: String
    let description: String?
    let milestone: Milestone?
    let tag: [Tag]?
//    let state: State

    enum CodingKeys: String, CodingKey {
        case id
        case title
        case description = "body"
        case milestone
        case tag = "labels"
//        case state
    }
}
