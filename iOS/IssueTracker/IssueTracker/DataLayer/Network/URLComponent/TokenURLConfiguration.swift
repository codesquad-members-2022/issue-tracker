//
//  UserInfoURLConfiguration.swift
//  IssueTracker
//
//  Created by Kai Kim on 2022/06/28.
//

import Foundation

struct TokenURLConfiguration: URLConfigurable {
    var queryItem: [URLQueryItem]?
    var path: String = "/login/oauth/github"
}
