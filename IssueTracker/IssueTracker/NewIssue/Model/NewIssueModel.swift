//
//  NewIssueModel.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/30.
//

import Foundation

class NewIssueModel {
    
    private let environment: NewIssueModelEnvironment
    
    init(environment: NewIssueModelEnvironment) {
        self.environment = environment
    }
    
    func createIssue(title: String, repo: Repository, content: String, label: Label?, milestone: Milestone?, assignee: Assignee?, completion: @escaping (Bool) -> Void) {
        environment.createIssue(title, repo, content, label, milestone, assignee) { boolResult in
            completion(boolResult)
        }
    }
}

struct NewIssueModelEnvironment {
    let createIssue: (String, Repository, String, Label?, Milestone?, Assignee?, @escaping (Bool) -> Void) -> Void
}
