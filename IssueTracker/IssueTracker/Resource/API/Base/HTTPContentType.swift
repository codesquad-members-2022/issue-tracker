//
//  HTTPContentType.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/15.
//

import Foundation

enum HTTPContentType: String {
    case json

    // TODO: - 컨텐트 타입이 어떤식으로 사용될지 고민
    var value: String {
        switch self {
        case .json:
            return "application/json; charset=utf-8"
        }
    }
}
