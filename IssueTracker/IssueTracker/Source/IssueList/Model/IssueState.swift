//
//  IssueState.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/14.
//

import Foundation

enum IssueState: String, Codable {
    case open
    case written
    case assign
    case comment
    case closed
}
