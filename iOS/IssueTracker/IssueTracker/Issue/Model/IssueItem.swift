//
//  IssueItem.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/16.
//

import Foundation

// MARK: - WelcomeElement
struct IssueItem: Codable {
    var id: Int?
    let title, content, milestoneName: String
    let labels: [Label]
    
    enum CodingKeys: String, CodingKey {
        case id
        case title, content
        case milestoneName = "milestoneTitle"
        case labels
    }
}

// MARK: - Label
struct Label: Codable {
    let title, backgroundColor: String
    let darkMode: Bool
    
    enum LabelCodingKeys: String, CodingKey {
        case title, backgroundColor
        case darkMode = "isDarkMode"
    }
}
