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

extension IssueItem {
    
    init?(_ issueVM: IssueViewModel) {
        guard let id = issueVM.id, let title = issueVM.title,
              let content = issueVM.content,let milestoneName = issueVM.milestoneName,
              let labels = issueVM.labels else {
            return nil
        }
        self.id = id
        self.title = title
        self.content = content
        self.milestoneName = milestoneName
        self.labels = labels
    }
    
}
