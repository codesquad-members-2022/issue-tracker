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
    
    static var empty: IssueDTO {
        IssueDTO(id: 0, node_id: "", url: "", repository_url: "", labels_url: "", comments_url: "", events_url: "", html_url: "", number: 1, state: "", title: "Test입니다.", body: "테스트 중입니다.", user: UserDTO.empty, labels: LabelDTO.empty, assignee: UserDTO.empty, assignees: [UserDTO.empty], milestone: MilestoneDTO.empty, locked: false, active_lock_reason: "", comments: 0, pull_request: PullRequestDTO.empty, created_at: "", updated_at: "", closed_by: UserDTO.empty, author_association: "")
    }
}
