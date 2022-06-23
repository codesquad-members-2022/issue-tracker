//
//  IssueTrackerTarget.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/15.
//

import Foundation

enum IssueTrackerTarget {
    case requestAuthorizeCode
    case requestAccessToken(code: String)
    case requestIssueList(token: String)
    case requestLoginStatus(token: String)
}

extension IssueTrackerTarget: BaseTarget {
    var baseURL: URL? {
        switch self {
        case .requestAccessToken, .requestAuthorizeCode:
            return URL(string: "https://github.com/login/oauth")
        case .requestIssueList:
            return URL(string: "https://api.github.com")
        case .requestLoginStatus:
            return URL(string: "https://api.github.com/user")
        }
    }
    // MARK: - End Point
    var path: String? {
        switch self {
        case .requestAccessToken:
            return "/access_token"
        case .requestAuthorizeCode:
            return "/authorize"
        case .requestIssueList:
            return "/repos/asqw887/issue-tracker/issues"
        default:
            return nil
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
        case .requestAuthorizeCode:
            return [
                "client_id": Environment.clientId,
                "scope": Environment.scope
            ]
        default:
            return nil
        }
    }

    var method: HTTPMethod {
        switch self {
        case .requestAccessToken:
            return .post
        case .requestIssueList, .requestAuthorizeCode, .requestLoginStatus:
            return .get
        }
    }

    var header: HTTPHeader? {
        switch self {
        case .requestAuthorizeCode:
            return nil
        case .requestAccessToken:
            return .oauth
        case .requestIssueList(let token), .requestLoginStatus(let token):
            return .githubAPIRequest(token: token)
        }
    }
}
