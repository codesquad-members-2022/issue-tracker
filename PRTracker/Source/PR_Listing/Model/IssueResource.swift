//
//  IssueResource.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/28.
//

import Foundation

struct IssueUpdateResource: APIResource {
    
    typealias ModelType = Issue
    
    // /repos/{owner}/{repo}/issues/{issue_number}
    let issue: Issue
    
    var path: String {
        guard let repository = issue.repository else {
            return ""
        }
        return "/repos/\(repository.fullName)/issues/\(issue.number)"
    }
    
    var query: [String: String]? {
        nil
    }
}

