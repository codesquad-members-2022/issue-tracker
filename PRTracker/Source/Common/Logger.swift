//
//  Logger.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/15.
//

import Foundation
import OSLog

struct Log {
    static func error(_ message: String) {
        os_log(.error, log: .default, "%@", message)
    }
    
    static func info(_ message: String) {
        os_log(.info, log: .default, "%@", message)
    }
}
