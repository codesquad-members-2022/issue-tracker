//
//  IssueDTO.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import Foundation

struct IssueDTO: Codable, Hashable {
    
    var id: Int
    var node_id: String
    var url: String
    var repository_url: String
    var labels_url: String
    var comments_url: String
    var events_url: String
    var html_url: String
    var number: Int
    var state: String
    var title: String
    var body: String
    var user: UserDTO
    var labels: LabelDTO
    var assignee: UserDTO
    var assignees: [UserDTO]
    var milestone: MilestoneDTO
    var locked: Bool
    var active_lock_reason: String
    var comments: Int
    var pull_request: PullRequestDTO
    var closed_at: String?
    var created_at: String
    var updated_at: String
    var closed_by: UserDTO
    var author_association: String
    
    func hash(into hasher: inout Hasher) {
        hasher.combine(id)
    }
    
    static func == (lhs: IssueDTO, rhs: IssueDTO) -> Bool {
        lhs.id == rhs.id
    }
}
