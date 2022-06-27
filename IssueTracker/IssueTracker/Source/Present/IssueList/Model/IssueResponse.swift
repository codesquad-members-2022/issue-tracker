//
//  IssueResponse.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/14.
//

import Foundation

struct IssueResponse: Codable {
    var statusCode: Int
    var body: [Issue]
}
