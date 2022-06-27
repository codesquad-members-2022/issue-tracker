//
//  NetworkService.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

protocol NetworkService {
    func execute<T: APIRequestable>(_ request: T, completion: @escaping (Result<T.ModelType, NetworkError>) -> Void)
}

struct NetworkManger: NetworkService {

    let session: URLSession

    init(urlSession: URLSession = URLSession.shared) {
        self.session = urlSession
    }
    
    func execute<T: APIRequestable>(_ request: T, completion: @escaping (Result<T.ModelType, NetworkError>) -> Void) {

        session.dataTask(with: request.request) { data, response, error in
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
            
            guard let decoded = request.decode(data) else {
                return completion(.failure(.failedDecoding(type: "\(T.ModelType.self)")))
            }
            
            completion(.success(decoded))
        }.resume()
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
