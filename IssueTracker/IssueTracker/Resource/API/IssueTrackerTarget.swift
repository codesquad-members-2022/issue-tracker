//
//  IssueTrackerTarget.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/15.
//

import Foundation

enum IssueTrackerTarget {
//    case requestGitHubAuthorize
    case requestAccessToken(code: String)
//    case requestLocalJson
}

extension IssueTrackerTarget: BaseTarget {
    var baseURL: URL? {
        switch self {
        case .requestAccessToken:
            return URL(string: "https://github.com")
        }
    }

    var path: String? {
        switch self {
        case .requestAccessToken:
            return "/login/oauth/authorize"
        }
    }

    var parameter: [String: String]? {
        switch self {
        case .requestAccessToken(let code):
            return [
                "client_id": "e6386d0321b6dc2820c0",
                "client_secret": "5f71ec5552ff96d0cac78118449404aec504fb64",
                "code": code
            ]
        }
    }

    var method: HTTPMethod {
        switch self {
        case .requestAccessToken:
            return .post
        }
    }

//    var content: HTTPContentType {
//        //
//    }

//    func addAuthorizationCode(_ code: String) {
//
//    }
}
