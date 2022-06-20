//
//  SignInNetworkTarget.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/16.
//

import Foundation

enum SignInNetworkTarget {
    case requestCode(clientID: String)
    case requestJWTTokenFromGitHub(code: String)
}

extension SignInNetworkTarget: NetworkTargetProtocol {
    var queryItem: [URLQueryItem]? {
        switch self {
        case .requestCode(let clientID):
            return [URLQueryItem(name: "client_id", value: clientID)]
        case .requestJWTTokenFromGitHub(let code):
            return [URLQueryItem(name: "code", value: code)]
        }
    }

    var url: String {
        return self.baseURL + self.path
    }

    var method: String? {
        switch self {
        case .requestCode:
            return nil
        case .requestJWTTokenFromGitHub:
            return "GET"
        }
    }
}

// MARK: - private computed properties
private extension SignInNetworkTarget {
    private var baseURL: String {
        switch self {
        case .requestJWTTokenFromGitHub:
            return "서버URL"
        default:
            return "https://github.com/login/oauth"
        }
    }

    private var path: String {
        switch self {
        case .requestCode:
            return "/authorize"
        case .requestJWTTokenFromGitHub:
            return "/github"
        }
    }
}
