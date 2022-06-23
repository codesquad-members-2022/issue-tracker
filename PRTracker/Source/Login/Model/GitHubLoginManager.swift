//
//  LoginModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation
import UIKit


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
    let uiApplication: UIApplication
    
    init(keyChainService: KeyChainService = KeyChainManager(),
         networkService: NetworkService = NetworkManger(),
         uiApplication: UIApplication = UIApplication.shared
    ) {
        self.keyChainService = keyChainService
        self.networkService = networkService
        self.uiApplication = uiApplication
    }
    
    func requestAuthorization() {
        guard var components = URLComponents(string: GitHubLoginManager.authorizeBaseURL) else { return }
        components.queryItems = [
            URLQueryItem(name: "client_id", value: Secrets.clientId),
            URLQueryItem(name: "scope", value: GitHubLoginManager.requiredScope)
        ]
        guard let url = components.url else { return }
        uiApplication.open(url)
    }
    
    func getAccessToken(with code: String, completion: @escaping (Bool) -> Void) {
        guard let url = makeAccessTokenURL(with: code) else { return }
        let request = makeAccessTokenRequest(with: url)
        
        networkService.request(request) { (response: TokenResponse?) -> Void in
            guard let response = response else {
                Log.error("Request for access token is failed. Code: \(code)")
                return completion(false)
            }
            
            keyChainService.save(response.accessToken,
                                 service: GitHubLoginManager.keyChainToken,
                                 account: GitHubLoginManager.keyChainAccount)
            completion(true)
        }
    }
    
    func hasValidToken(completion: @escaping (Bool) -> Void) {
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

