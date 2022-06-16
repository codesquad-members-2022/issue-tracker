//
//  Issue.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/16.
//

import Foundation

struct Issue: Codable {
    let title: String
    let body: String?
    let state: String
    let labels: [Label]
    let milestone: Milestone?
    
    enum CodingKeys: String, CodingKey {
        case title, body, state, labels, milestone
    }
}
