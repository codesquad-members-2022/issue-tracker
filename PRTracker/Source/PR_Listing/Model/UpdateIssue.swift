//
//  UpdateIssue.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/28.
//

import Foundation

// 이 타입은 다음과 같은 Endpoint를 나타낸다.
// [PATCH] /repos/{owner}/{repo}/issues/{issue_number}

struct UpdateIssue: APIEndpoint {
    typealias ModelType = Issue
    
    let issue: Issue
    
    var httpMethod: HTTPMethod {
        return .patch
    }
    
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

