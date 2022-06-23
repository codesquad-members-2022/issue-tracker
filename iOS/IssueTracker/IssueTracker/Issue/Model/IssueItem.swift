//
//  IssueItem.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/16.
//

import Foundation

struct IssueItem {
    let id: Int
    let title, content: String
    let milestoneName: String
    let labels: [Label]
    
}

struct Label {
    let title: String
    let backgroundColor: String
}
