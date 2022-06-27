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
        let context = "(\(#file), \(#function), \(#line))"
        os_log(.error, log: .default, "%@", message + context)
    }
    
    static func info(_ message: String) {
        let context = "(\(#file), \(#function), \(#line))"
        os_log(.info, log: .default, "%@", message + context)
    }
    
    static func check(_ message: String) {
        let context = "(\(#file), \(#function), \(#line))"
        if #available(iOS 14.0, *) {
            let log = Logger()
            log.info("\(message + context)")
        }
    }
}
