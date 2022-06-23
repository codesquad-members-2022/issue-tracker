//
//  Repository.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/23.
//

import Foundation

enum NetworkError: Error {
    case responseError
    case responseDataEmptyError
    case requestFailedError
    case decodeFailedError
}

enum HTTPMethod: String {
    case post = "POST"
}

class Repository {
    struct TokenBag: Decodable {
        let accessToken: String
    }

    private let userDefaults = UserDefaults.standard
    private let session: URLSession

    init(session: URLSession = URLSession.shared) {
        self.session = session
    }

    private func getCode() -> String? {
        let code = UserDefaults.standard.string(forKey: "AuthorizationCode")
        return code
    }

    func getToken(completion: @escaping (Result<TokenBag, NetworkError>) -> Void) {
        // 코드 주고 토큰 받아와
        guard let url = makeTokenURL() else {
            return
        }

        var request = URLRequest(url: url)
        request.httpMethod = HTTPMethod.post.rawValue
        request.addValue("application/json", forHTTPHeaderField: "Accept")

        session.dataTask(with: request) { data, response, error in
            if let error = self.getError(data: data, response: response, error: error) {
                completion(.failure(error))
                return
            }

            do {
                guard let decodedResponse = try? JSONDecoder().decode(TokenBag.self, from: data!) else {
                    return
                }
                completion(.success(decodedResponse))
            } catch {
                completion(.failure((.decodeFailedError)))
            }
        }.resume()
    }

    func setToken(token: TokenBag, completion: @escaping (Bool) -> Void) {
        userDefaults.setValue(token, forKey: "AuthToken")
        completion(true)
    }

    private func makeTokenURL() -> URL? {
        let code = getCode()
        let url = "https://github.com/login/oauth/access_token"

        guard var components = URLComponents(string: url) else {
            return nil
        }
        components.queryItems = [
            URLQueryItem(name: "client_id", value: "1f7a746c1d01cc6de0bf"),
            URLQueryItem(name: "code", value: code)
        ]
        return components.url
    }

    private func getError(data: Data?, response: URLResponse?, error: Error?) -> NetworkError? {
        guard let httpResponse = response as? HTTPURLResponse, (200 ... 299) ~= httpResponse.statusCode else {
            return .responseError
        }
        guard data != nil else {
            return .responseDataEmptyError
        }
        guard error != nil else {
            return .requestFailedError
        }

        return nil
    }
}
