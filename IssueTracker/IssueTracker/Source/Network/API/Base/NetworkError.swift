//
//  NetworkError.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/15.
//

import Foundation

enum NetworkError: Error {

    case invalieURLError
    case transportError
    case serverError(code: Int)
    case missingData
    case decodingError

}
