//
//  RequestRepository.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/17.
//

import Foundation

final class Provider {

    static func request(with request: URLRequest, _ completion: @escaping (Data) -> Void) {
        URLSession.shared.dataTask(with: request) { data, response, error in
            if let error = error {
                print(error.localizedDescription)
                return
            }
            guard let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode.isSuccess else {
                  return
            }
            guard let data = data else { return }
            completion(data)
        }.resume()
    }

    // TODO: - switch와 IF 문으로 최소한 으로 줄이기.
    // TODO: - Target이 .requestAuthorizeCode 일 때 URL 이 필요한데 이 로직을 빼서
    /// static func makeURL(with target: IssueTrackerTarget) -> URL? { }
    /// 이런 형식으로 만들어야 하나? 고민
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
        case .requestAccessToken, .requestIssueList:
            var request = URLRequest(url: url)

            if let param = target.parameter {
                let requestBody = try? JSONSerialization.data(withJSONObject: param, options: .init())
                request.httpBody = requestBody
            }
            if let content = target.content {
                request.addValue(content.value, forHTTPHeaderField: content.forHTTPHeaderField)
            }
            if let accept = target.accept {
                request.addValue(accept.value, forHTTPHeaderField: accept.forHTTPHeaderField)
            }
            if let token = target.authorization {
                request.addValue(token.value, forHTTPHeaderField: token.fotHttpHeaderField)
            }
            request.httpMethod = target.method.value

            return request
        }
    }
}
