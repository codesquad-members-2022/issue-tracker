//
//  APIRequest.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

// Resource 타입이 Codable할 때 사용가능한 타입
struct APIRequest<Resource: APIResource>: APIRequestable where Resource.ModelType: Codable {
    
    typealias ModelType = Resource.ModelType
    
    let session: URLSession
    let resource: Resource
    let httpMethod: HTTPMethod
    let header: [String: String]
    let body: String
    
    // Default Initialzier
    init(resource: Resource,
         httpMethod: HTTPMethod = .get,
         header: [String: String] = [:],
         body: String = "",
         session: URLSession = .shared
    ) {
        self.resource = resource
        self.httpMethod = httpMethod
        self.header = header
        self.body = body
        self.session = session
    }
    
    // Initialize with token
    init(resource: Resource,
         httpMethod: HTTPMethod = .get,
         token: String,
         header: [String: String] = [:],
         body: String = "",
         session: URLSession = .shared
    ) {
        var header = header
        header["Authorization"] = "token \(token)"
        self.init(resource: resource, httpMethod: httpMethod, header: header, body: body, session: session)
    }
    
    var request: URLRequest {
        var request = URLRequest(url: resource.url)
        request.httpMethod = httpMethod.rawValue
        request.httpBody = body.data(using: .utf8)
        
        defaultHeader.forEach { (key, value) in
            request.setValue(value, forHTTPHeaderField: key)
        }
        header.forEach { (key, value) in
            request.setValue(value, forHTTPHeaderField: key)
        }
        
        return request
    }
}
