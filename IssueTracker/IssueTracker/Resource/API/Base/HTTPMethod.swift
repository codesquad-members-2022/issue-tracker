//
//  HTTPMethod.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/15.
//

import Foundation

enum HTTPMethod: String {
    case get, post

    var value: String {
        self.rawValue.uppercased()
    }
}
