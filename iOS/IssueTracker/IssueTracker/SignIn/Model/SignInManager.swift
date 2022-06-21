//
//  SignInManager.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/15.
//

import Foundation

protocol SignInManagable {
    func requestCode(completion: @escaping (Result<URL, Error>) -> Void)
    func requestJWTToken(codeURL: URL, completion: @escaping (Result<[String: String], NetworkError>) -> Void)
}

struct SignInManager {
    private let clientID = Bundle.main.clientID
    private let clientSecret = Bundle.main.clientSecret
    var urlSession: URLSessionProtocol
    
    init(urlSession: URLSessionProtocol = URLSession.shared) {
        self.urlSession = urlSession
    }
}

// MARK: - SignInManagable Method
extension SignInManager: SignInManagable {
    func requestCode(completion: @escaping (Result<URL, Error>) -> Void) {
        let networkTarget = SignInNetworkTarget.requestCode(clientID: clientID)
        var components = URLComponents(string: networkTarget.url) ?? URLComponents()
        components.queryItems = networkTarget.queryItem

        guard let url = components.url else {
            return completion(.failure(NetworkError.invalidURL))
        }
        completion(.success(url))
    }

    func requestJWTToken(codeURL: URL, completion: @escaping (Result<[String: String], NetworkError>) -> Void) {
        if let code = URLComponents(string: codeURL.absoluteString)?
            .queryItems?
            .filter({ $0.name == "code" })
            .first?.value {
            NetworkService<[String: String]>.fetchData(
                target: SignInNetworkTarget.requestJWTTokenFromGitHub(code: code),
                urlSession: urlSession,
                completion: completion)
        }
    }
}
