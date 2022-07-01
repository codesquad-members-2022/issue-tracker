//
//  SignInInfo.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/07/01.
//

import Foundation

struct SignInInfo: Codable {
    let userID: Int
    let imageURL: String
    let accessToken, refreshToken: String

    enum CodingKeys: String, CodingKey {
        case userID = "userId"
        case imageURL = "imgUrl"
        case accessToken, refreshToken
    }
}
