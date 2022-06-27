//
//  APIResource.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

// REST API 중에서 '자원(Resource)'를 추상화한 타입

protocol APIResource {
    associatedtype ModelType
    var url: URL { get }
    var path: String { get }
    var query: [String: String]? { get }
}

extension APIResource {
    var url: URL {
        var components = URLComponents(string: "https://api.github.com")!
        components.path = path
        components.queryItems = query?.map { (key, value) in
            return URLQueryItem(name: key, value: value)
        }
        return components.url!
    }
}
