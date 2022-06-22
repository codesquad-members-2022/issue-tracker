//
//  HTTPContentType.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/15.
//

import Foundation

enum HTTPContentType: String {
    case json

    var value: String {
        switch self {
        case .json:
            return "application/json"
        }
    }

    var forHTTPHeaderField: String {
        switch self {
        case .json:
            return "Content-Type"
        }
    }

}
