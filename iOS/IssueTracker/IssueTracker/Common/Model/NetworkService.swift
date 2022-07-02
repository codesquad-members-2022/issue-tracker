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

        let jwtToken = UserDefaultManager.getJWTToken() ?? ""

        if let url = components.url {
            var request = URLRequest(url: url)
            request.httpMethod = target.method
            request.httpBody = target.body
            request.addValue("application/json", forHTTPHeaderField: "Content-Type")
            request.addValue("Bearer \(jwtToken)", forHTTPHeaderField: "Authorization")

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

    var message: String {
        switch self {
        case .invalidURL:
            return "잘못된 URL을 사용하고 있습니다."
        case .transferError:
            return "서버와 연결이 되지 않습니다."
        case .noData, .noResponse, .decodingError:
            return "서버와 연결에서 문제가 발생했습니다."
        case .serverError(let statusCode):
            return "status code: \(statusCode)에 해당하는 문제가 발생했습니다."
        }
    }
}
