//
//  LoginModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation
import UIKit


struct OAuthManger {
    
    static let shared = OAuthManger()
    
    static let clientId = "3d45d9cbd8c6e918841f"
    static let requiredScope = "repo,user"
    static let authorizeBaseURL = "https://github.com/login/oauth/authorize"
    static let accessTokenURL = "https://github.com/login/oauth/access_token"
    
    let keyChainManager = KeyChainManager()
    
    func requestAuthorization() {
        guard var components = URLComponents(string: OAuthManger.authorizeBaseURL) else { return }
        components.queryItems = [
            URLQueryItem(name: "client_id", value: OAuthManger.clientId),
            URLQueryItem(name: "scope", value: OAuthManger.requiredScope)
        ]
        guard let url = components.url else { return }
        UIApplication.shared.open(url)
    }
    
    func requestToken(with code: String) {
        guard let url = makeURL(with: code) else { return }
        
        var request = URLRequest(url: url)
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        
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
    
    func makeURL(with code: String) -> URL? {
        guard var components = URLComponents(string: OAuthManger.accessTokenURL) else { return nil }
        components.queryItems = [
            URLQueryItem(name: "client_id", value: Secrets.clientId),
            URLQueryItem(name: "client_secret", value: Secrets.clientSecret),
            URLQueryItem(name: "code", value: code)
        ]
        return components.url
    }
    
    func getClientSecret() -> String {
        guard let filePath = Bundle.main.path(forResource: "secret", ofType: "plist") else {
            Log.error("Failed to find secret.plist")
            return ""
        }
        
        let plistKey = "github_client_secret"
        let plist = NSDictionary(contentsOfFile: filePath)
        
        guard let clientScret = plist?.object(forKey: plistKey) as? String else {
            Log.error("Failed to find value for \(plistKey)")
            return ""
        }
        
        return clientScret
    }
}

