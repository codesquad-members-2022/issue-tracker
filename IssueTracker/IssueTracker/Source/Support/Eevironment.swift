//
//  Eevironment.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/17.
//

import Foundation

public enum Environment {
    enum Keys {
        enum Plist {
            static let clientId = "CLIENT_ID"
            static let clientSecret = "CLIENT_SECRET"
            static let scope = "ROLE_SCOPE"
        }
    }

    private static let infoDictionary: [String: Any] = {
        guard let dict = Bundle.main.infoDictionary else {
            fatalError("Plist file not found")
        }
        return dict
    }()

    static let clientId: String = {
        guard let idString = Environment.infoDictionary[Keys.Plist.clientId] as? String else { fatalError("Client id not set in plist for this environment") }
        return idString
    }()

    static let clientSecret: String = {
        guard let secretString = Environment.infoDictionary[Keys.Plist.clientSecret] as? String else { fatalError("Client secret not set in plist for this environment") }
        return secretString
    }()

    static let scope: String  = {
        guard let scopeString = Environment.infoDictionary[Keys.Plist.scope] as? String else { fatalError("scope not set in plist for this environment") }
        return scopeString
    }()
}
