//
//  LabelItem.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import Foundation

struct LabelItem: Codable {
    var id: Int?
    let title: String
    let description: String?
    let backgroundColor, isDarkMode: String
}
