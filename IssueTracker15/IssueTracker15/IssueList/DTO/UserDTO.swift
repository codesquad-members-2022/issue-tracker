//
//  UserDTO.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import Foundation

struct UserDTO: Codable {
    static var empty: UserDTO {
        UserDTO(login: "", id: 0, node_id: "", avatar_url: "", gravatar_id: "", url: "", html_url: "", followers_url: "", following_url: "", gists_url: "", starred_url: "", subscriptions_url: "", organizations_url: "", repos_url: "", events_url: "", received_events_url: "", type: "", site_admin: false)
    }
    var login: String
    var id: Int
    var node_id: String
    var avatar_url: String
    var gravatar_id: String
    var url: String
    var html_url: String
    var followers_url: String
    var following_url: String
    var gists_url: String
    var starred_url: String
    var subscriptions_url: String
    var organizations_url: String
    var repos_url: String
    var events_url: String
    var received_events_url: String
    var type: String
    var site_admin: Bool
}
