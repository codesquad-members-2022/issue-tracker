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
    case error(Error)
}

enum HTTPMethod: String {
    case post = "POST"
}

class AuthRepository {
    private let storage = Storage()
    private let service = Service()

    func getToken(completion: @escaping (Result<TokenBag, NetworkError>) -> Void) {
        guard let code = storage.getCode() else {
            completion(.failure(.invalidAuthCode))
            return
        }

        service.getToken(code: code) { data, response, error in
            self.storage.deleteCode()

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
        }
    }

    func setToken(token: String) {
        storage.setToken(token: token)
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
}
