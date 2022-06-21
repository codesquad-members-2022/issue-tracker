//
//  DummyResponse.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/21.
//

import Foundation

enum DummyResponse: String {
    static let format = "json"
    case user = "dummyResponseUser"
    case issues = "dummyResponseIssues"
    
    var data: Data? {
        guard let url = Bundle.main.url(forResource: self.rawValue, withExtension: Self.format),
              let dummyData = try? Data(contentsOf: url) else {
            return nil
        }
        return dummyData
    }
}
