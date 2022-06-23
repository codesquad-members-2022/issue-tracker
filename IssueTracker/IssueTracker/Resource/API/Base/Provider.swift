//
//  RequestRepository.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/17.
//

import Foundation

final class Provider {

    static func request(with request: URLRequest, completion: @escaping (Result<Data?,
                                                                        NetworkError>) -> Void) {
        URLSession.shared.dataTask(with: request) { data, response, error in
            if let _ = error {
                completion(.failure(.transportError))
                return
            }
            guard let httpResponse = response as? HTTPURLResponse else { completion(.failure(.transportError))
                return }

            guard httpResponse.statusCode.isSuccess else {
                completion(.failure(.serverError(code: httpResponse.statusCode)))
                return
            }
            guard let data = data else { return }
            completion(.success(data))

        }.resume()
    }

    static func makeURLRequest(with target: IssueTrackerTarget) -> URLRequest? {
        guard var url = target.baseURL else { return nil }
        if let path = target.path {
            url = url.appendingPathComponent(path)
        }
        switch target {
        case .requestAuthorizeCode:
            guard var components = URLComponents(string: url.absoluteString),
                  let params = target.parameter else { return nil }
            components.queryItems = [
                URLQueryItem(name: "client_id", value: params["client_id"]),
                URLQueryItem(name: "scope", value: params["scope"])
            ]
            guard let resultUrl = components.url else { return nil }
            return URLRequest(url: resultUrl)
        default:
            var request = URLRequest(url: url)
            if let param = target.parameter {
                let requestBody = try? JSONSerialization.data(withJSONObject: param, options: .init())
                request.httpBody = requestBody
            }
            if let header = target.header {
                for (key, value) in  header.dictionary {
                    request.addValue(value, forHTTPHeaderField: key)
                }
                request.httpMethod = target.method.value
            }

            return request
        }
    }
}
