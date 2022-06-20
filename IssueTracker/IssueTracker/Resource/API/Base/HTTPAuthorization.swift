//
//  HTTPAuthorization.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/20.
//

import Foundation

enum HTTPAuthorization {
    case header(token: String)

    var value: String {
        switch self {
        case .header(let token):
            return "token \(token)"
        }
    }

    var fotHttpHeaderField: String {
        switch self {
        case .header:
            return "Authorization"
        }
    }
}
