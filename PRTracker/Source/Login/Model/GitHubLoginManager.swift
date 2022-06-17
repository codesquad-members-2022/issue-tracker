//
//  LoginModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation
import UIKit


struct GitHubLoginManager {
    
    static let shared = GitHubLoginManager()
    
    static let requiredScope = "repo,user"
    static let authorizeBaseURL = "https://github.com/login/oauth/authorize"
    static let accessTokenURL = "https://github.com/login/oauth/access_token"
    
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
    
    func getAccessToken(with code: String) {
        guard let url = makeAccessTokenURL(with: code) else { return }
        let request = makeAccessTokenRequest(with: url)

        networkService.get(request: request) { (response: TokenResponse?) -> Void in
            guard let response = response else {
                Log.error("Network Request for access token is failed")
                return
            }
            keyChainService.save(response.accessToken, service: "access-token", account: "github")
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

