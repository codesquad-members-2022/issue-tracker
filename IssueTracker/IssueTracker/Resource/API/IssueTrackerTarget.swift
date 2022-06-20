//
//  IssueTrackerTarget.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/15.
//

import Foundation

enum IssueTrackerTarget {
    case requestAccessToken(code: String)
    case requestIssueList(token: String)
}

extension IssueTrackerTarget: BaseTarget {
    var baseURL: URL? {
        switch self {
        case .requestAccessToken:
            return URL(string: "https://github.com")
        case .requestIssueList:
            return URL(string: "https://api.github.com")
        }
    }
    // MARK: - End Point
    var path: String? {
        switch self {
        case .requestAccessToken:
            return "/login/oauth/access_token"
        case .requestIssueList:
            return "/repos/asqw887/issue-tracker/issues"
        }
    }

    // TODO: KeyChain 사용해서 client_id, client_secret 등은 안보이게 하는 것이 좋겠다.
    var parameter: [String: String]? {
        switch self {
        case .requestAccessToken(let code):
            return [
                "client_id": Environment.clientId,
                "client_secret": Environment.clientSecret,
                "code": code
            ]
        case .requestIssueList:
            return nil
        }
    }

    var method: HTTPMethod {
        switch self {
        case .requestAccessToken:
            return .post
        case .requestIssueList:
            return .get
        }
    }

    var content: HTTPContentType? {
        switch self {
        case .requestAccessToken:
            return .json
        case .requestIssueList:
            return nil
        }
    }

    var accept: HTTPAcceptType? {
        switch self {
        case .requestAccessToken:
            return .loginJson
        case .requestIssueList:
            return .issueJson
        }
    }

    var authorization: HTTPAuthorization? {
        switch self {
        case .requestAccessToken:
            return nil
        case .requestIssueList(let token):
            return .header(token: token)
        }
    }
}
