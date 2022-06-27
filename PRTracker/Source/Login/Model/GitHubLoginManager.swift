//
//  LoginModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation
import UIKit


enum AuthorizationStatus {
    case authorized
    case failed
    case none
}

struct GitHubLoginManager {
    typealias AccessToken = String
    
    static let shared = GitHubLoginManager()
    
    static let requiredScope = "repo,user"
    static let authorizeBaseURL = "https://github.com/login/oauth/authorize"
    static let accessTokenURL = "https://github.com/login/oauth/access_token"
    static let validateTokenURL = "https://github.com/applications/\(Secrets.clientId)/token"
    static let keyChainAccount = "github"
    static let keyChainToken = "access-token"
    
    let keyChainService: KeyChainService
    let networkService: NetworkService
    
    init(keyChainService: KeyChainService = KeyChainManager(),
         networkService: NetworkService = NetworkManger()) {
        self.keyChainService = keyChainService
        self.networkService = networkService
    }
    
    func requestAuthorization() {
        guard var components = URLComponents(string: GitHubLoginManager.authorizeBaseURL) else { return }
        components.queryItems = [
            URLQueryItem(name: "client_id", value: Secrets.clientId),
            URLQueryItem(name: "scope", value: GitHubLoginManager.requiredScope)
        ]
        guard let url = components.url else { return }
        UIApplication.shared.open(url)
    }
    
    let authorization: Observable<AuthorizationStatus> = Observable(.none)
    
    func getAccessToken(with code: String) {
        guard let url = makeAccessTokenURL(with: code) else { return }
        let request = makeAccessTokenRequest(with: url)
        
        networkService.request(request) { (result: Result<TokenResponse, NetworkError>) in
            switch result {
            case .success(let data):
                keyChainService.save(data.accessToken,
                                     service: GitHubLoginManager.keyChainToken,
                                     account: GitHubLoginManager.keyChainAccount)
                authorization.value = .authorized
                
            case .failure(let error):
                Log.error(error.localizedDescription)
                authorization.value = .failed
            }
        }
    }
    
    func checkAuthorization(completion: @escaping (Bool) -> Void) {
        // User 요청이 성공하면 유효한 것으로 판단
        UserManager().getCurrentUser { user in
            if user == nil { return completion(false) }
            return completion(true)
        }
    }
    
    private func makeAccessTokenURL(with code: String) -> URL? {
        guard var components = URLComponents(string: GitHubLoginManager.accessTokenURL) else { return nil }
        components.queryItems = [
            URLQueryItem(name: "client_id", value: Secrets.clientId),
            URLQueryItem(name: "client_secret", value: Secrets.clientSecret),
            URLQueryItem(name: "code", value: code)
        ]
        return components.url
    }
    
    private func makeAccessTokenRequest(with url: URL) -> URLRequest {
        var request = URLRequest(url: url)
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        return request
    }
}

