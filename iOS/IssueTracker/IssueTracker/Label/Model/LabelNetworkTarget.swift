//
//  LabelNetworkTarget.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import Foundation

enum LabelNetworkTarget: NetworkTargetProtocol {
    case requestLabels
    case addNewLabel(newLabel: LabelItem)

    var url: String {
        return baseURL + path
    }
    
    var queryItem: [URLQueryItem]? {
        switch self {
        case .requestLabels, .addNewLabel:
            return nil
        }
    }
    
    var method: String? {
        switch self {
        case .requestLabels:
            return "GET"
        case .addNewLabel:
            return "POST"
        }
    }
    
    var body: Data? {
        switch self {
        case .requestLabels:
            return nil
        case .addNewLabel(let newLabel):
            let parameter: [String: Any] = [
                "title": newLabel.title,
                "backgroundColor": newLabel.backgroundColor,
                "darkMode": newLabel.isDarkMode
            ]

            let body = try? JSONSerialization.data(withJSONObject: parameter)
            return body
        }
    }
}

private extension LabelNetworkTarget {
    var baseURL: String {
        return "http://3.37.101.82:8080"
    }

    var path: String {
        switch self {
        case .requestLabels, .addNewLabel:
            return "/labels"
        }
    }
}
