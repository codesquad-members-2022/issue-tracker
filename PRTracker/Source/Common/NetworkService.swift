//
//  NetworkService.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

protocol NetworkService {
    func get<T: Codable>(request: URLRequest, then completion: @escaping (T?) -> Void)
}

struct NetworkManger: NetworkService {
    
    let session: URLSession
    
    init(urlSession: URLSession = URLSession.shared) {
        self.session = urlSession
    }
    
    func get<T: Codable>(request: URLRequest, then completion: @escaping (T?) -> Void) {
        session.dataTask(with: request) { data, _, error in
            if let error = error {
                Log.error(error.localizedDescription)
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
