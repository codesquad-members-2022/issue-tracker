//
//  PullRequestDTO.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import Foundation

struct PullRequestDTO: Codable {
    var url: String
    var html_url: String
    var diff_url: String
    var patch_url: String
}
