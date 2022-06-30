//
//  APIRequest.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

// Resource 타입이 Codable할 때 사용가능한 타입
struct APIRequest<Endpoint: APIEndpoint>: APIRequestable where Endpoint.ModelType: Codable {
    
    typealias ModelType = Endpoint.ModelType
    
    let session: URLSession
    let endpoint: Endpoint
    let header: [String: String]
    let body: String
    
    // Default Initialzier
    init(endpoint: Endpoint,
         header: [String: String] = [:],
         body: String = "",
         session: URLSession = .shared
    ) {
        self.endpoint = endpoint
        self.header = header
        self.body = body
        self.session = session
    }
    
    // Initialize with token
    init(endpoint: Endpoint,
         token: String,
         header: [String: String] = [:],
         body: String = "",
         session: URLSession = .shared
    ) {
        var header = header
        header["Authorization"] = "token \(token)"
        self.init(endpoint: endpoint, header: header, body: body, session: session)
    }
    
    var request: URLRequest {
        var request = URLRequest(url: endpoint.url)
        request.httpMethod = endpoint.httpMethod.rawValue
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
