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
    
    func getAccessToken(with code: String) {
        guard let url = makeAccessTokenURL(with: code) else { return }
        let request = makeAccessTokenRequest(with: url)
        
        networkService.request(request, method: .get) { (response: TokenResponse?) -> Void in
            guard let response = response else {
                Log.error("Network Request for access token is failed")
                return
            }
            keyChainService.save(response.accessToken, service: "access-token", account: "github")
        }
    }
    
    func validateToken(_ token: String, completion: @escaping (AccessToken?) -> Void) {
        // Static하게 저장해둔 URL String이 실패할 경우, error handling보단 runtime crash가 나서 string을 고치는 것이 빠르지 않을까?
        let url = URL(string: GitHubLoginManager.validateTokenURL)!
        let request = makeValidateTokenRequest(with: url, token: token)
        
        // 포스트 요청을 보내고 나서 response가 nil이면 유효하지 않은 토큰으로 판단한다.
        networkService.request(request, method: .post) { (response: TokenValidatationResponse?) -> Void in
            guard let response = response else {
                return completion(nil)
            }
            
            // 유효 기간 만료 시간이 10분 이하로 남았을 때는 사용 흐름을 방해할 수 있으므로 false로 return해서 재발급.
            guard response.expiresAt.timeIntervalSinceNow <= 600 else {
                resetToken(request: request, completion: completion)
                return
            }
            
            return completion(token)
        }
    }
    
    private func resetToken(request: URLRequest, completion: @escaping (AccessToken?) -> Void) {
        networkService.request(request, method: .patch) { (response: TokenValidatationResponse?) -> Void in
            guard let response = response else {
                return completion(nil)
            }
            return completion(response.accessToken)
        }
    }
    
    private func makeValidateTokenRequest(with url: URL, token: String) -> URLRequest {
        var request = URLRequest(url: url, with: token)
        request.httpBody = try? JSONSerialization.data(withJSONObject: ["access_token": token], options: .prettyPrinted)
        return request
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
        request.setValue("application/vnd.github.v3+json", forHTTPHeaderField: "Accept")
        return request
    }
}

