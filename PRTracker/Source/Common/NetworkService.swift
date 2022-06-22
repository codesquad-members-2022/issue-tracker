//
//  NetworkService.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

protocol NetworkService {
    func request<T: Codable>(_ request: URLRequest, method: HTTPMethod, then completion: @escaping (T?) -> Void)
}

enum HTTPMethod: String {
    case get = "GET"
    case post = "POST"
    case patch = "PATCH"
    case delete = "DELETE"
}

struct NetworkManger: NetworkService {
    
    let session: URLSession
    
    init(urlSession: URLSession = URLSession.shared) {
        self.session = urlSession
    }
    
    func request<T: Codable>(_ request: URLRequest, method: HTTPMethod, then completion: @escaping (T?) -> Void) {
        var request = request
        request.httpMethod = "\(method)"
        
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
    
}
