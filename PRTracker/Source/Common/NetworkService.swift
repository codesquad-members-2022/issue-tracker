//
//  NetworkService.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

protocol NetworkService {
    func request<T: Codable>(_ request: URLRequest, method: HTTPMethod, mediaType: MediaType, then completion: @escaping (Result<T, NetworkError>) -> Void)
}

extension NetworkService {
    // Method 미지정시 default는 Get
    func request<T: Codable>(_ request: URLRequest, then completion: @escaping (Result<T, NetworkError>) -> Void) {
        self.request(request, method: .get, mediaType: .json, then: completion)
    }
    
    // mediaType 미지정시 default는 json
    func request<T: Codable>(_ request: URLRequest, method: HTTPMethod, then completion: @escaping (Result<T, NetworkError>) -> Void) {
        self.request(request, method: method, mediaType: .json, then: completion)
    }
}


enum HTTPMethod: String {
    case get = "GET"
    case post = "POST"
    case patch = "PATCH"
    case delete = "DELETE"
}

enum MediaType: String {
    case json = "application/json"
}

enum NetworkError: Error {
    case networkFailure(error: Error)
    case failedParsingHTTPResponse
    case unexpectedStatusCode(_ statusCode: Int)
    case missingData
    case failedDecoding(type: String)
}

extension NetworkError: LocalizedError {
    var errorDescription: String? {
        switch self {
            
        case .networkFailure(let error):
            return NSLocalizedString(error.localizedDescription, comment: "Failed Networking")
        case .failedParsingHTTPResponse:
            return NSLocalizedString("Cannot parse HTTP response", comment: "Cannot parse HTTP response")
        case .unexpectedStatusCode(let code):
            return NSLocalizedString("Unexpected Status Code: \(code)", comment: "Unexpected Status Code")
        case .missingData:
            return NSLocalizedString("Missing data", comment: "Missing data")
        case .failedDecoding(let type):
            return NSLocalizedString("Decoding for \(type) failed", comment: "Failed Decoding")
        }
    }
}

struct NetworkManger: NetworkService {
    
    let session: URLSession
    
    init(urlSession: URLSession = URLSession.shared) {
        self.session = urlSession
    }
    
    func request<T: Codable>(_ request: URLRequest, method: HTTPMethod, mediaType: MediaType, then completion: @escaping (Result<T, NetworkError>) -> Void) {
        let request = configure(request, method: method, mediaType: mediaType)
        
        session.dataTask(with: request) { data, response, error in
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
            
            guard let decoded = try? JSONDecoder().decode(T.self, from: data) else {
                return completion(.failure(.failedDecoding(type: "\(T.self)")))
            }
            
            completion(.success(decoded))
        }.resume()
    }
    
    private func configure(_ request: URLRequest, method: HTTPMethod, mediaType: MediaType) -> URLRequest {
        var request = request
        request.httpMethod = "\(method)"
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        return request
    }
}
