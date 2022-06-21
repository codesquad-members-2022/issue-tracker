//
//  LabelDTO.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import Foundation

struct LabelDTO: Codable {
    var id: Int
    var node_id: String
    var url: String
    var name: String
    var desc: String
    var color: String
    /// Original Name : default
    var isDefault: Bool
    
    enum CodingKeys: String, CodingKey {
        case id
        case node_id
        case url
        case name
        case desc = "description"
        case color
        case isDefault = "default"
    }
}
