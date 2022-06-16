//
//  LoginModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation
import UIKit


struct OAuthManager {
    
    static let shared = OAuthManager()
    
    static let requiredScope = "repo,user"
    static let authorizeBaseURL = "https://github.com/login/oauth/authorize"
    static let accessTokenURL = "https://github.com/login/oauth/access_token"
    
    let keyChainManager = KeyChainManager()
    
    func requestAuthorization() {
        guard var components = URLComponents(string: OAuthManager.authorizeBaseURL) else { return }
        components.queryItems = [
            URLQueryItem(name: "client_id", value: Secrets.clientId),
            URLQueryItem(name: "scope", value: OAuthManager.requiredScope)
        ]
        guard let url = components.url else { return }
        UIApplication.shared.open(url)
    }
    
    func requestToken(with code: String) {
        guard let url = makeTokenAccessURL(with: code) else { return }
        let request = makeTokenAccessRequest(with: url)

        URLSession.shared.dataTask(with: request) { data, _, error in
            if let error = error {
                Log.error(error.localizedDescription)
                return
            }
            
            guard let data = data else {
                Log.error("Missing data")
                return
            }
            
            guard let tokenReponse = try? JSONDecoder().decode(TokenResponse.self, from: data) else {
                Log.error("Decoding failed")
                return
            }
            
            keyChainManager.save(tokenReponse.accessToken, service: "access-token", account: "github")
            
        }.resume()
    }
    
    func makeTokenAccessURL(with code: String) -> URL? {
        guard var components = URLComponents(string: OAuthManager.accessTokenURL) else { return nil }
        components.queryItems = [
            URLQueryItem(name: "client_id", value: Secrets.clientId),
            URLQueryItem(name: "client_secret", value: Secrets.clientSecret),
            URLQueryItem(name: "code", value: code)
        ]
        return components.url
    }
    
    func makeTokenAccessRequest(with url: URL) -> URLRequest {
        var request = URLRequest(url: url)
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        return request
    }
}

