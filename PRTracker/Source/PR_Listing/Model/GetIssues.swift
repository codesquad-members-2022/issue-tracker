//
//  GetIssues.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

struct GetIssues: APIEndpoint {
    
    typealias ModelType = [Issue]
    
    let parameter: IssuesParameter
    
    init(parameter: IssuesParameter) {
        self.parameter = parameter
    }
    
    var httpMethod: HTTPMethod {
        return .get
    }
    
    var path: String {
        return "/issues"
    }
    
    var query: [String: String]? {
        var queries = [String: String]()
        queries["filter"] = parameter.filter.rawValue
        queries["state"] = parameter.state.rawValue
        queries["sort"] = parameter.sort.rawValue
        queries["direction"] = parameter.isDescending ? "desc" : "asc"
        queries["pulls"] = parameter.isPull ? "true" : "false"
        return queries
    }
}

