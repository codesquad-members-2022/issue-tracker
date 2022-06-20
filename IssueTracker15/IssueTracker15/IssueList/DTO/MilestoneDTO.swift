//
//  MilestoneDTO.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import Foundation

struct MilestoneDTO: Codable {
    var url: String
    var html_url: String
    var labels_url: String
    var id: Int
    var node_id: String
    var number: Int
    var state: String
    var title: String
    var description: String
    var creator: UserDTO
    var open_issues: Int
    var closed_issues: Int
    var created_at: String
    var updated_at: String
    var closed_at: String
    var due_on: String
}
