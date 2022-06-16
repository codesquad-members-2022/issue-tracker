//
//  Milestone.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/16.
//

import Foundation

// MARK: - Milestone
struct Milestone: Codable {
    let title: String
    let description: String
    let openIssues, closedIssues: Int
    let state: String
    let dueOn: String? // Date?

    enum CodingKeys: String, CodingKey {
        case title
        case description = "description"
        case openIssues = "open_issues"
        case closedIssues = "closed_issues"
        case state
        case dueOn = "due_on"
    }
}
