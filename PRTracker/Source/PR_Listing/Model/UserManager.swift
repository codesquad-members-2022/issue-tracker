//
//  UserManger.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

struct UserManager {
    
    
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
        
        guard let url = URL(string: BaseURL.user) else {
            Log.error("Wrong Base URL: \(BaseURL.user)")
            return completion(nil)
        }
        
        let request = URLRequest(url: url, with: accessToken)
        
        networkService.request(request, method: .get, then: completion)
    }
}
