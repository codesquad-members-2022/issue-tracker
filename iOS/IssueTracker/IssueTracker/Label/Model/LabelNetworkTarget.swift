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
        return "https://008b1557-6228-4eb0-af91-8ea0225787e5.mock.pstmn.io"
    }

    var path: String {
        switch self {
        case .requestLabels, .addNewLabel:
            return "/labels"
        }
    }
}
