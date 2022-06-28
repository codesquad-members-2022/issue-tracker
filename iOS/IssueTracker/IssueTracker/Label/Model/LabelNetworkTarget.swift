//
//  LabelNetworkTarget.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import Foundation

enum LabelNetworkTarget: NetworkTargetProtocol {
    case requestLabels

    var url: String {
        return baseURL + path
    }
    
    var queryItem: [URLQueryItem]? {
        switch self {
        case .requestLabels:
            return nil
        }
    }
    
    var method: String? {
        switch self {
        case .requestLabels:
            return "GET"
        }
    }
    
    var body: Data? {
        switch self {
        case .requestLabels:
            return nil
        }
    }
}

private extension LabelNetworkTarget {
    var baseURL: String {
        switch self {
        case .requestLabels:
            return "https://008b1557-6228-4eb0-af91-8ea0225787e5.mock.pstmn.io"
        }
    }

    var path: String {
        switch self {
        case .requestLabels:
            return "/labels"
        }
    }
}
