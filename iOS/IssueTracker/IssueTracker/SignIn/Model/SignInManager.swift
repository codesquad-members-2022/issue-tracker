//
//  SignInManager.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/15.
//

import Foundation

struct SignInManager {
    private let clientID = Bundle.main.clientID
    private let clientSecret = Bundle.main.clientSecret

    func requestCode(completion: @escaping (Result<URL, Error>) -> Void) {
        let networkTarget = SignInNetworkTarget.requestCode(clientID: clientID)
        var components = URLComponents(string: networkTarget.url) ?? URLComponents()
        components.queryItems = networkTarget.queryItem

        guard let url = components.url else {
            return completion(.failure(NetworkError.invalidURL))
        }
        completion(.success(url))
    }

    func requestAccessToken(codeURL: URL, completion: @escaping (Result<[String: String], NetworkError>) -> Void) {
        if codeURL.absoluteString.starts(with: "issuetrackerapp://"),
           let code = codeURL.absoluteString.split(separator: "=").last.map({String($0)}) {
            NetworkManager<[String: String]>.fetchData(
                target: SignInNetworkTarget.requestAccessToken(clientID: clientID,
                                            clientSecret: clientSecret,
                                            code: code),
                completion: completion)
        }
    }
}
