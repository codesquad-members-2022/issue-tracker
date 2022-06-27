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
        guard var components = URLComponents(string: Self.authorizeBaseURL) else { return }
        components.queryItems = [
            URLQueryItem(name: "client_id", value: Secrets.clientId),
            URLQueryItem(name: "scope", value: Self.requiredScope)
        ]
        guard let url = components.url else { return }
        UIApplication.shared.open(url)
    }
    
    let authorization: Observable<AuthorizationStatus> = Observable(.none)
    
    func requestAccessToken(with code: String) {
        let tokenResource = TokenResource(clientID: Secrets.clientId, clientSecret: Secrets.clientSecret, code: code)
        let tokenRequest = APIRequest(resource: tokenResource, httpMethod: .post)
        
        networkService.execute(tokenRequest) { result in
            switch result {
            case .success(let data):
                keyChainService.save(data.accessToken, service: Self.keyChainToken, account: Self.keyChainAccount)
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
}

