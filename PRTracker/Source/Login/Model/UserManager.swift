//
//  UserManger.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

struct UserManager {
    
    static let userURL = "https://api.github.com/user"
    let keyChainService: KeyChainService
    let networkService: NetworkService
    
    init(keyChainService: KeyChainService = KeyChainManager(),
         networkService: NetworkService = NetworkManger()) {
        self.keyChainService = keyChainService
        self.networkService = networkService
    }
    
    func getCurrentUser(then completion: @escaping (User?) -> Void) {
        guard let accessToken = keyChainService.load(service: "access-token", account: "github") else {
            Log.error("Access Token is not found")
            return completion(nil)
        }
        
        guard let url = URL(string: Self.userURL) else {
            Log.error("User API URL is wrong")
            return completion(nil)
        }
        
        let request = URLRequest(url: url, with: accessToken)
        
        networkService.get(request: request, then: completion)
    }
}
