//
//  IssueNetworkTarget.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/24.
//

import Foundation

enum IssueNetworkTarget {
    case issuesList, createIssue
    case issueListDetail(id: Int), deleteIssue(id: Int)
}

extension IssueNetworkTarget: NetworkTargetProtocol {
    var queryItem: [URLQueryItem]? {
        switch self {
        case .issuesList:
            return nil
        case .issueListDetail:
            return nil
        case .createIssue:
            return nil
        case .deleteIssue:
            return nil
        }
    }

    var url: String {
        return self.baseURL + self.path
    }

    var method: String? {
        switch self {
        case .issuesList, .issueListDetail:
            return "GET"
        case .createIssue:
            return "POST"
        case .deleteIssue:
            return "DEL"
        }
    }
}

private extension IssueNetworkTarget {
    private var baseURL: String {
        return "https://008b1557-6228-4eb0-af91-8ea0225787e5.mock.pstmn.io"
    }
    
    private var path: String {
        switch self {
        case .issuesList, .createIssue:
            return "/issues"
        case .issueListDetail(let id), .deleteIssue(let id):
            return "/issues/\(id)"
        }
    }
}
