//
//  SignInNetworkTarget.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/16.
//

import Foundation

enum SignInNetworkTarget {
    case requestCode(clientID: String)
    case requestAccessToken(clientID: String, clientSecret: String, code: String)
}

extension SignInNetworkTarget: NetworkTargetable {
    var queryItem: [URLQueryItem]? {
        switch self {
        case .requestCode(let clientID):
            return [URLQueryItem(name: "client_id", value: clientID)]
        case .requestAccessToken(let clientID, let clientSecret, let code):
            return [
                URLQueryItem(name: "client_id", value: clientID),
                URLQueryItem(name: "client_secret", value: clientSecret),
                URLQueryItem(name: "code", value: code)
            ]
        }
    }

    var url: String {
        return self.baseURL + self.path
    }

    var method: String? {
        switch self {
        case .requestCode:
            return nil
        case .requestAccessToken:
            return "GET"
        }
    }

    var isAcceptJSON: Bool {
        switch self {
        case .requestCode:
            return false
        case .requestAccessToken:
            return true
        }
    }
}

// MARK: - private computed properties
private extension SignInNetworkTarget {
    private var baseURL: String {
        return "https://github.com/login/oauth"
    }

    private var path: String {
        switch self {
        case .requestCode:
            return "/authorize"
        case .requestAccessToken:
            return "/access_token"
        }
    }
}
