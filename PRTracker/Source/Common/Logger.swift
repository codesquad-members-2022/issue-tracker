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
    
    static func check(_ message: String) {
        if #available(iOS 14.0, *) {
            let log = Logger()
            log.info("Message: \(message)")
        }
    }
}
