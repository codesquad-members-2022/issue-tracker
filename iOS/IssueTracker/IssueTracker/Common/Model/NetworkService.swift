//
//  NetworkService.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/16.
//

import Foundation

struct NetworkService<T: Decodable> {
    static func fetchData(target: NetworkTargetProtocol, urlSession: URLSessionProtocol, completion: @escaping (Result<T, NetworkError>) -> Void) {
        guard let request = makeRequest(target: target) else {
            return completion(.failure(NetworkError.invalidURL))
        }

        urlSession.dataTask(with: request) { (data, response, error) in
            guard error == nil else {
                return completion(.failure(NetworkError.transferError))
            }
            guard let data = data else {
                return completion(.failure(NetworkError.noData))
            }
            guard let response = response as? HTTPURLResponse else {
                return completion(.failure(NetworkError.noResponse))
            }

            let statusCode = response.statusCode
            guard 200..<300 ~= statusCode else {
                return completion(.failure(NetworkError.serverError(statusCode: statusCode)))
            }

            guard let decodedData = try? JSONDecoder().decode(T.self, from: data) else {
                return completion(.failure(NetworkError.decodingError))
            }

            completion(.success(decodedData))
        }.resume()
    }
}

private extension NetworkService {
    static func makeRequest(target: NetworkTargetProtocol) -> URLRequest? {
        var components = URLComponents(string: target.url) ?? URLComponents()
        components.queryItems = target.queryItem

        if let url = components.url {
            var request = URLRequest(url: url)
            request.httpMethod = target.method
            if let target = target as? SignInNetworkTarget,
               target.isAcceptJSON {
                request.addValue("application/json", forHTTPHeaderField: "Accept")
            }

            return request
        }
        return nil
    }
}

enum NetworkError: Error {
    case invalidURL
    case transferError
    case noData
    case noResponse
    case serverError(statusCode: Int)
    case decodingError
}
