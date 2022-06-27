//
//  MilestoneDTO.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import Foundation

struct MilestoneDTO: Codable {
    static var empty: MilestoneDTO {
        MilestoneDTO(url: "", html_url: "'", labels_url: "", id: 0, node_id: "", number: 0, state: "", title: "", description: "", creator: UserDTO.empty, open_issues: 0, closed_issues: 0, created_at: "", updated_at: "", closed_at: "", due_on: "")
    }
    
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
