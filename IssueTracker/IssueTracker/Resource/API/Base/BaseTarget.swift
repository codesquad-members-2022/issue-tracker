//
//  BaseTarget.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/15.
//

import Foundation

// TODO: URLSession .request 에 맞춰서 Target 작성
protocol BaseTarget {
    var baseURL: URL? { get }
    var path: String? { get }
    var parameter: [String: String]? { get }
    var method: HTTPMethod { get }
    var content: HTTPContentType? { get }
    var accept: HTTPAcceptType? { get }
    var authorization: HTTPAuthorization? { get }
}

extension BaseTarget {
    var header: [String: String]? {
        ["Content-Type": content?.value ?? ""]
    }
}
