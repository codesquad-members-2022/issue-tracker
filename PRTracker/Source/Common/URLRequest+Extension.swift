//
//  URLRequest+Extension.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/20.
//

import Foundation

extension URLRequest {
    init(url: URL, with token: String) {
        self.init(url: url)
        self.setValue("application/json", forHTTPHeaderField: "Accept")
        self.setValue("token \(token)", forHTTPHeaderField: "Authorization")
    }
}
