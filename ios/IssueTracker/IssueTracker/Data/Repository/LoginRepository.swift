////
////  LoginRepository.swift
////  IssueTracker
////
////  Created by Jihee hwang on 2022/06/22.
////
//
// import Foundation
//
// struct TokenBag: Decodable {
//    let accessToken: String
// }
//
// struct TokenParams: Encodable {
//    let clientId: String
//    let clientSecret: String
//    let code: String
// }
//
// protocol OAuthClient {
//    func exchangeCodeForToken(code: String, completion: @escaping (Result<TokenBag, NetworkError>) -> Void)
// }
//
// enum NetworkError: Error {
//    case responseError
//    case responseDataEmptyError
//    case requestFailedError
//    case decodeFailedError
// }
//
// enum HTTPMethod: String {
//    case post = "POST"
// }
//
// class OAuth: OAuthClient {
//    let session: URLSession
//    init(session: URLSession = URLSession.shared) {
//        self.session = session
//    }
//
//    func exchangeCodeForToken(code: String, completion: @escaping (Result<TokenBag, NetworkError>) -> Void) {
//        let params = TokenParams(clientId: "", clientSecret: "", code: "")
//
//        guard let url = makeTokenURL(with: code) else {
//            return
//        }
//        var request = URLRequest(url: url)
//        request.httpMethod = HTTPMethod.post.rawValue
//        request.addValue("application/json", forHTTPHeaderField: "Accept")
//
//        guard let body = try? JSONSerialization.data(withJSONObject: params, options: .prettyPrinted) else {
//            return
//        }
//        request.httpBody = body
//
//        session.dataTask(with: request) { data, response, error in
//            if let error = self.getError(data: data, response: response, error: error) {
//                completion(.failure(error))
//                return
//            }
//
//            do {
//                guard let decodedResponse = try? JSONDecoder().decode(TokenBag.self, from: data!) else {
//                    return
//                }
//                completion(.success(decodedResponse))
//            } catch {
//                completion(.failure((.decodeFailedError)))
//            }
//        }.resume()
//        session.finishTasksAndInvalidate()
//    }
//
//    func makeTokenURL(with code: String) -> URL? {
//        let url = "https://github.com/login/oauth/access_token"
//
//        guard var components = URLComponents(string: url) else {
//            return nil
//        }
//        components.queryItems = [
//            URLQueryItem(name: "client_id", value: ""),
//            URLQueryItem(name: "client_secret", value: ""),
//            URLQueryItem(name: "code", value: code)
//        ]
//        return components.url
//    }
//
//    func getError(data: Data?, response: URLResponse?, error: Error?) -> NetworkError? {
//        guard let httpResponse = response as? HTTPURLResponse, (200 ... 299) ~= httpResponse.statusCode else {
//            return .responseError
//        }
//
//        if data == nil {
//            return .responseDataEmptyError
//        }
//
//        if error != nil {
//            return .requestFailedError
//        }
//
//        return nil
//    }
// }
