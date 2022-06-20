//
//  NetworkError.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/15.
//

import Foundation

enum NetworkError: Error {
    case invalidJsonError
    case invalidUrlError
    case invalidTarget
    case cantReachedServerError
}
