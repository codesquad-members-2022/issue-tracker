//
//  NewIssueModel.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/30.
//

import Foundation

class NewIssueModel {
    
    private let service: IssueService
    
    init(service: IssueService) {
        self.service = service
    }
    
    func createIssue(title: String, repo: Repository, content: String, label: Label?, milestone: Milestone?, assignee: Assignee?, completion: @escaping (Bool) -> Void) {
        service.createIssue(title: title, repo: repo, content: content, label: label, milestone: milestone, assignee: assignee) { boolResult in
            completion(boolResult)
        }
    }
}
