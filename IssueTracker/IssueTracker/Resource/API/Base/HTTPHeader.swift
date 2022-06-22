//
//  HTTPHeader.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/21.
//

import Foundation

enum HTTPHeader {
    case oauth
    case githubAPIRequest(token: String)

    var header: [String: String] {
        switch self {
        case .oauth:
            return ["Content-Type": "application/json", "Accept": "application/json"]
        case .githubAPIRequest(let token):
            return ["Content-Type": "application/json", "Accept": "application/vnd.github.v3+json", "Authorization": "token \(token)"]
        }
    }

}
