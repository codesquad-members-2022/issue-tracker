//
//  AuthRepository.swift
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
    case invalidAuthCode
    case invalidURL
    case encodingError
}

enum HTTPMethod: String {
    case post = "POST"
}

class AuthRepository {
    struct TokenDTO: Encodable {
        let code: String
    }

    struct TokenBag: Decodable {
        let token: String
    }

    private let userDefaults = UserDefaults.standard
    private let session: URLSession

    init(session: URLSession = URLSession.shared) {
        self.session = session
    }

    private func getCode() -> String? {
        let code = UserDefaults.standard.string(forKey: LocalStorageConstants.AuthCode)
        return code
    }

    func getToken(completion: @escaping (Result<TokenBag, NetworkError>) -> Void) {
        guard let url = makeTokenURL() else {
            completion(.failure(.invalidURL))
            return
        }

        guard let code = getCode() else {
            completion(.failure(.invalidAuthCode))
            return
        }

        guard let body = try? JSONEncoder().encode(TokenDTO(code: code)) else {
            completion(.failure(.encodingError))
            return
        }

        var request = URLRequest(url: url)
        request.httpMethod = HTTPMethod.post.rawValue
        request.addValue("application/json", forHTTPHeaderField: "Accept")
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = body

        session.dataTask(with: request) { data, response, error in
            self.deleteCode()

            if let error = self.getError(
                data: data,
                response: response,
                error: error
            ) {
                completion(.failure(error))
                return
            }

            guard let data = data else {
                return
            }

            do {
                guard let decodedResponse = try? JSONDecoder().decode(TokenBag.self, from: data) else {
                    return
                }

                completion(.success(decodedResponse))
            } catch {
                completion(.failure((.decodeFailedError)))
            }
        }.resume()
    }

    func setToken(token: String) {
        userDefaults.setValue(token, forKey: LocalStorageConstants.AuthToken)
    }

    private func makeTokenURL() -> URL? {
        let url = "https://us-central1-onboarding-5054d.cloudfunctions.net/github/auth"
        return URL(string: url)
    }

    private func getError(data: Data?, response: URLResponse?, error: Error?) -> NetworkError? {
        guard let httpResponse = response as? HTTPURLResponse, (200 ... 299) ~= httpResponse.statusCode else {
            return .responseError
        }

        guard data != nil else {
            return .responseDataEmptyError
        }

        guard error == nil else {
            return .requestFailedError
        }

        return nil
    }

    private func deleteCode() {
        userDefaults.removeObject(forKey: LocalStorageConstants.AuthCode)
    }
}
