//
//  IssuesResource.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

struct IssuesResource: APIResource {
    typealias ModelType = [Issue]
    
    let filter: IssueFilter
    let state: IssueState
    let sort: IssueSort
    let isDescending: Bool
    let isPull: Bool
    
    init(filter: IssueFilter = .assigned, state: IssueState = .open, sort: IssueSort = .created, isDescending: Bool = true, isPull: Bool = true) {
        self.filter = filter
        self.state = state
        self.sort = sort
        self.isDescending = isDescending
        self.isPull = isPull
    }
    
    var path: String {
        return "/issues"
    }
    
    var query: [String: String]? {
        var queries = [String: String]()
        queries["filter"] = filter.rawValue
        queries["state"] = state.rawValue
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
