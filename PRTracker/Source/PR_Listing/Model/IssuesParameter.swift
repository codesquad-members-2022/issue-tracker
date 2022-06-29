//
//  IssuesParameter.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/29.
//

import Foundation

struct IssuesParameter {
    let filter: IssueFilter
    let state: IssueState
    let sort: IssueSort
    let isDescending: Bool
    let isPull: Bool
    
    static let standard = IssuesParameter(filter: .assigned,
                                         state: .open,
                                         sort: .created,
                                         isDescending: true,
                                         isPull: false)
}

enum IssueFilter: String {
    case assigned
    case created
    case mentioned
    case subscribed
    case repose
    case all
}

enum IssueState: String {
    case open
    case closed
    case all
}

enum IssueSort: String {
    case created
    case updated
    case comments
}
