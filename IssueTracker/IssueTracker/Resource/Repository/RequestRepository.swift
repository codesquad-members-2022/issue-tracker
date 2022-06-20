//
//  RequestRepository.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/17.
//

import Foundation

final class RequestRepository {

    static func sendRequest(with request: URLRequest, _ completion: @escaping (Data) -> Void) {
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

    static func makeURLRequest(with target: BaseTarget) -> URLRequest? {
        guard var url = target.baseURL else { return nil }
        if let path = target.path {
            url = url.appendingPathComponent(path)
        }
        var request = URLRequest(url: url)

        if let param = target.parameter {
            let requestBody = try! JSONSerialization.data(withJSONObject: param, options: .init())
            request.httpBody = requestBody
        }
        request.setValue(target.content.value, forHTTPHeaderField: target.content.forHTTPHeaderField)
        if let accept = target.accept {
            request.addValue(accept.value, forHTTPHeaderField: accept.forHTTPHeaderField)
        }
        request.httpMethod = target.method.value

        return request
    }
}
