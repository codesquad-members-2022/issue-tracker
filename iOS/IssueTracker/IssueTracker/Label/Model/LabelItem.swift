//
//  LabelItem.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import Foundation

struct LabelItem: Codable {
    let id: Int
    let title, description, backgroundColor, isDarkMode: String
}
