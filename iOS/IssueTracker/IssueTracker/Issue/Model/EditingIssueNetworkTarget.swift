//
//  EditingIssueNetworkTarget.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/23.
//

import Foundation

enum EditingIssueNetworkTarget {
    case sendIssue(newIssue: IssueItem)
}

extension EditingIssueNetworkTarget: NetworkTargetProtocol {
    var url: String {
        return baseURL + path
    }
    
    var queryItem: [URLQueryItem]? {
        switch self {
        case .sendIssue:
            return nil
        }
    }
    
    var method: String? {
        switch self {
        case .sendIssue:
            return "POST"
        }
    }

    var body: Data? {
        switch self {
        case .sendIssue(let newIssue):
            let dictionary: [String: Any] = [
                "title": newIssue.title,
                "content": newIssue.content,
                "milestoneTitle": "",
                "labels": [""],
                "assignee": [""]
            ]
            let body = try? JSONSerialization.data(withJSONObject: dictionary)
            return body
        }
    }
}

private extension EditingIssueNetworkTarget {
    private var baseURL: String {
        return "https://008b1557-6228-4eb0-af91-8ea0225787e5.mock.pstmn.io"
    }

    private var path: String {
        switch self {
        case .sendIssue:
            return "/issues"
        }
    }
}
