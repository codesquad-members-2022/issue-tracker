//
//  IssuesResource.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

struct IssuesResource: APIResource {
    typealias ModelType = Issue
    
    let filter: IssueFilter = .assigned
    let state: IssueState = .open
    let sort: IssueSort = .created
    let isDescending: Bool = true
    let isPull: Bool = true
    
    var path: String {
        return "/issues"
    }
    
    var query: [String: String]? {
        var queries = [String: String]()
        queries["filter"] = filter.rawValue
        queries["State"] = state.rawValue
        queries["sort"] = sort.rawValue
        queries["direction"] = isDescending ? "desc" : "asc"
        queries["pulls"] = isPull ? "true" : "false"
        return queries
    }
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
