//
//  IssueNetworkTarget.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/24.
//

import Foundation

enum IssueNetworkTarget {
    case issuesList, addNewIssue(newIssue: IssueItem)
    case issueListDetail(id: Int), deleteIssue(id: Int)
}

extension IssueNetworkTarget: NetworkTargetProtocol {
    var queryItem: [URLQueryItem]? {
        return nil
    }

    var url: String {
        return self.baseURL + self.path
    }

    var method: String? {
        switch self {
        case .issuesList, .issueListDetail:
            return "GET"
        case .addNewIssue:
            return "POST"
        case .deleteIssue:
            return "DEL"
        }
    }

    var body: Data? {
        switch self {
        case .addNewIssue(let newIssue):
            let parameter: [String: Any] = [
                "title": newIssue.title,
                "content": newIssue.content,
                "milestoneTitle": newIssue.milestoneName,
                "label": ["0"],
                "assignee": ["0"]
            ]

            let body = try? JSONSerialization.data(withJSONObject: parameter)
            return body
        default:
            return nil
        }
    }
}

private extension IssueNetworkTarget {
    private var baseURL: String {
        return "http://3.37.101.82:8080"
    }
    
    private var path: String {
        switch self {
        case .issuesList, .addNewIssue:
            return "/issues"
        case .issueListDetail(let id), .deleteIssue(let id):
            return "/issues/\(id)"
        }
    }
}
