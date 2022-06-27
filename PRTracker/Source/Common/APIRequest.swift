//
//  APIRequest.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

protocol Requestable {
    associatedtype ModelType

    var request: URLRequest { get }
    func decode(_ data: Data) -> ModelType?
    
    func execute(completion: @escaping (Result<ModelType, NetworkError>) -> Void)
}

extension Requestable where ModelType: Decodable {
    func decode(_ data: Data) -> ModelType? {
        return try? JSONDecoder().decode(ModelType.self, from: data)
    }
    
    var defaultHeader: [String: String] {
        return ["Accept": "application/json"]
    }
}

struct APIRequest<Resource: APIResource>: Requestable {
    typealias ModelType = Resource.ModelType
    
    let resource: Resource
    let httpMethod: HTTPMethod
    let header: [String: String]
    let body: String
    
    // Default Initialzier
    init(resource: Resource,
         httpMethod: HTTPMethod = .get,
         header: [String: String] = [:],
         body: String = "") {
        self.resource = resource
        self.httpMethod = httpMethod
        self.header = header
        self.body = body
    }
    
    // Initialize with token
    init(resource: Resource,
         httpMethod: HTTPMethod = .get,
         token: String,
         header: [String: String] = [:],
         body: String = "") {
        var header = header
        header["Authorization"] = "token \(token)"
        self.init(resource: resource, httpMethod: httpMethod, header: header, body: body)
    }
    
    var request: URLRequest {
        var request = URLRequest(url: resource.url)
        request.httpMethod = httpMethod.rawValue
        request.httpBody = body.data(using: .utf8)
        
        defaultHeader.forEach { (key, value) in
            request.setValue(key, forHTTPHeaderField: value)
        }
        header.forEach { (key, value) in
            request.setValue(key, forHTTPHeaderField: value)
        }
        
        return request
    }
    
    func execute(completion: @escaping (Result<Resource.ModelType, NetworkError>) -> Void) {
        URLSession.shared.dataTask(with: request) { data, response, error in
            if let error = error {
                return completion(.failure(.networkFailure(error: error)))
            }
            
            guard let statusCode = (response as? HTTPURLResponse)?.statusCode else {
                return completion(.failure(.failedParsingHTTPResponse))
            }
            
            guard (200..<300).contains(statusCode) else {
                return completion(.failure(.unexpectedStatusCode(statusCode)))
            }
            
            guard let data = data else {
                return completion(.failure(.missingData))
            }
            
            guard let decoded = decode(data) else {
                return completion(.failure(.failedDecoding(type: "\(Resource.ModelType.self)")))
            }
            
            completion(.success(decoded))
        }.resume()
    }
}
