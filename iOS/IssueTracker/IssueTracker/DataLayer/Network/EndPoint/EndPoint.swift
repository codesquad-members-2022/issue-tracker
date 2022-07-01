//
//  EndPoint.swift
//  IssueTracker
//
//  Created by Kai Kim on 2022/06/16.
//

import Foundation

struct EndPoint: EndPointable, HTTPPackageable {

    let mehtod: HTTPMethod
    let headerType: String = "Content-Type"
    let headerValue: String = "application/json"
    let body: Data?
    let urlConfigure: URLConfigurable

    init(urlConfigure: URLConfigurable, method: HTTPMethod, body: Data?) {
        self.urlConfigure = urlConfigure
        self.mehtod = method
        self.body = body
    }

    var url: URL {
        var components = URLComponents()
        components.scheme = urlConfigure.scheme
        components.host = urlConfigure.host
        components.path = urlConfigure.path
        components.queryItems = urlConfigure.queryItem
        guard let url = components.url else {
            preconditionFailure("Invalid URL components: \(components)"
            )}
        return url
    }
}
