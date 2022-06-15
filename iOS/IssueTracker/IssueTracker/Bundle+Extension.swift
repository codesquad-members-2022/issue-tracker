//
//  Bundle+Extension.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/15.
//

import Foundation

extension Bundle {
    var clientID: String {
        guard let file = path(forResource: "ClientKey", ofType: "plist"),
              let resource = NSDictionary(contentsOfFile: file) else { return "" }

        guard let id = resource["client_id"] as? String else {
            fatalError("ClientKey.plist에 client_id를 설정해주세요.")
        }

        return id
    }

    var clientSecret: String {
        guard let file = path(forResource: "ClientKey", ofType: "plist"),
              let resource = NSDictionary(contentsOfFile: file) else { return "" }

        guard let clientSecret = resource["client_secret"] as? String else {
            fatalError("ClientKey.plist에 client_secret를 설정해주세요.")
        }

        return clientSecret
    }
}
