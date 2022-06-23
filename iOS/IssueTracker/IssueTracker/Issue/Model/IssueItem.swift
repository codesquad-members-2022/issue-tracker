//
//  IssueItem.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/16.
//

import Foundation

struct IssueItem: Codable {
    let id: Int
    let title, content: String
    let milestoneName: String
    let labels: [Label]
    
    enum CodingKeys: String, CodingKeys {
        case id
        case title, content
        case milestoneName = "milestoneTitle"
        case label
    }
}

struct Label {
    let title: String
    let backgroundColor: String
    let isDarkMode: Bool
}
