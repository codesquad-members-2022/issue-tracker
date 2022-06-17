//
//  IssueTrackerTarget.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/15.
//

import Foundation

enum IssueTrackerTarget {
    case requestGitHubAuthorize
    case requestAccessToken(code: String)
}

extension IssueTrackerTarget: BaseTarget {
    var baseURL: URL? {
        switch self {
        case .requestAccessToken, .requestGitHubAuthorize:
            return URL(string: "https://github.com/login/oauth")
        }
    }
    // MARK: - End Point
    var path: String? {
        switch self {
        case .requestGitHubAuthorize:
            return "/authorize"
        case .requestAccessToken:
            return "/login/oauth/access_token"
        }
    }

    // TODO: KeyChain 사용해서 client_id, client_secret 등은 안보이게 하는 것이 좋겠다.
    var parameter: [String: String]? {
        switch self {
        case .requestGitHubAuthorize:
            return [
                "client_id": Environment.clientId,
                "scope": Environment.scope
            ]
        case .requestAccessToken(let code):
            return [
                "client_id": Environment.clientId,
                "client_secret": Environment.clientSecret,
                "code": code
            ]
        }
    }

    var method: HTTPMethod {
        switch self {
        case .requestAccessToken:
            return .post
        case .requestGitHubAuthorize:
            return .get
        }
    }

    var content: HTTPContentType {
        switch self {
        case .requestAccessToken:
            return .json

        }
    }

    var accept: HTTPAcceptType? {
        switch self {
        case .requestAccessToken:
            return .json
        }
    }

}
