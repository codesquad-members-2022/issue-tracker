//
//  DeepLink.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/22.
//

import Foundation

typealias AuthCode = String

enum DeepLinkURLPath: String {
    case login
}

enum DeepLink: Equatable {
    case home
    case login(AuthCode?)

    static func build(with url: URL) -> DeepLink? {
        guard let components = URLComponents(url: url, resolvingAgainstBaseURL: true), let params = components.queryItems else {
            return nil
        }

        guard let pageID = params.first(where: { $0.name == "page_id" })?.value else {
            return nil
        }

        let path = DeepLinkURLPath(rawValue: pageID)

        switch path {
        case .login:
            let code = params.first { $0.name == "code" }?.value
            return DeepLink.login(code)
        default:
            return nil
        }
    }
}
