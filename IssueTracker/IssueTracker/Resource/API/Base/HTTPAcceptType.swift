//
//  HTTPAcceptType.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/16.
//

import Foundation

enum HTTPAcceptType: String {
    case json

    // TODO: - 컨텐트 타입이 어떤식으로 사용될지 고민
    var value: String {
        switch self {
        case .json:
            return "application/json"
        }
    }

    var forHTTPHeaderField: String {
        switch self {
        case .json:
            return "Accept"
        }
    }

}
