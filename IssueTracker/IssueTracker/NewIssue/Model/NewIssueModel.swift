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
    
    func createIssue(newIssue: NewIssueFormat, completion: @escaping (Bool) -> Void) {
        environment.createIssue(newIssue) { boolResult in
            completion(boolResult)
        }
    }
}

struct NewIssueModelEnvironment {
    let createIssue: (NewIssueFormat, @escaping (Bool) -> Void) -> Void
}
