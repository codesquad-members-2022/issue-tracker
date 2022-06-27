//
//  Service.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/23.
//

import Foundation

struct TokenDTO: Encodable {
    let code: String
}

struct TokenBag: Decodable {
    let token: String
}

final class Service {
    private let session: URLSession

    init(session: URLSession = URLSession.shared) {
        self.session = session
    }

    private func makeTokenURL() -> URL? {
        let url = "https://us-central1-onboarding-5054d.cloudfunctions.net/github/auth"
        return URL(string: url)
    }

    func getToken(code: String, completion: @escaping (Data?, URLResponse?, NetworkError?) -> Void) {
        guard let url = makeTokenURL() else {
            completion(nil, nil, .invalidURL)
            return
        }

        guard let body = try? JSONEncoder().encode(TokenDTO(code: code)) else {
            completion(nil, nil, .encodingError)
            return
        }

        var request = URLRequest(url: url)
        request.httpMethod = HTTPMethod.post.rawValue
        request.addValue("application/json", forHTTPHeaderField: "Accept")
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = body

        session.dataTask(with: request) { data, response, error in
            guard let error = error else {
                completion(data, response, nil)
                return
            }

            completion(data, response, .error(error))
        }.resume()
    }
}
