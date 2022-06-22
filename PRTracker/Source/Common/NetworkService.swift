//
//  NetworkService.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

protocol NetworkService {
    func request<T: Codable>(_ request: URLRequest, method: HTTPMethod, mediaType: MediaType, then completion: @escaping (T?) -> Void)
}

extension NetworkService {
    // Method 미지정시 default는 Get
    func request<T: Codable>(_ request: URLRequest, then completion: @escaping (T?) -> Void) {
        self.request(request, method: .get, mediaType: .json, then: completion)
    }
    
    // mediaType 미지정시 default는 json
    func request<T: Codable>(_ request: URLRequest, method: HTTPMethod, then completion: @escaping (T?) -> Void) {
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

struct NetworkManger: NetworkService {
    
    let session: URLSession
    
    init(urlSession: URLSession = URLSession.shared) {
        self.session = urlSession
    }
    
    func request<T: Codable>(_ request: URLRequest, method: HTTPMethod, mediaType: MediaType, then completion: @escaping (T?) -> Void) {
        let request = configure(request, method: method, mediaType: mediaType)
        
        session.dataTask(with: request) { data, response, error in
            if let error = error {
                Log.error(error.localizedDescription)
                return
            }
            
            guard let statusCode = (response as? HTTPURLResponse)?.statusCode else {
                Log.error("Cannot parse HTTP reponse status code")
                return
            }
            
            guard (200..<300).contains(statusCode) else {
                Log.error("Unexpected Status Code: \(statusCode)")
                return
            }
            
            guard let data = data else {
                Log.error("Missing data")
                return
            }
            
            guard let decoded = try? JSONDecoder().decode(T.self, from: data) else {
                Log.error("Decoding failed")
                return
            }
            
            completion(decoded)
        }.resume()
    }
    
    private func configure(_ request: URLRequest, method: HTTPMethod, mediaType: MediaType) -> URLRequest {
        var request = request
        request.httpMethod = "\(method)"
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        return request
    }
}
