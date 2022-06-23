//
//  IssueItem.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/16.
//

import Foundation

// MARK: - WelcomeElement
struct IssueItem: Codable {
    let id: Int
    let title, content, milestoneName: String
    let labels: [Label]
    
    enum CodingKeys: String, CodingKey {
        case id
        case title, content
        case milestoneName = "milestoneTitle"
        case labels = "label"
    }
}

// MARK: - Label
struct Label: Codable {
    let title, backgroundColor: String
    let isDarkMode: Bool
}

//extension IssueItem{
//    init?(_ issueVM: IssueViewModel) {
//        guard let id = issueVM.id, let title = issueVM.title,
//              let content = issueVM.content,let milestoneName = issueVM.milestoneName,
//              let labels = issueVM.labels else {
//            return nil
//        }
//        self.id = id
//        self.title = title
//        self.content = content
//        self.milestoneName = milestoneName
//        self.labels = labels
//    }
//}
