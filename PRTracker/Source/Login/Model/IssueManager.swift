//
//  PullManager.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

struct IssueManager {
    
    let keyChainService: KeyChainService
    let networkService: NetworkService
    
    static let issueURL = "https://api.github.com/issues"
    
    init(keyChainService: KeyChainService = KeyChainManager(),
         networkService: NetworkService = NetworkManger()) {
        self.keyChainService = keyChainService
        self.networkService = networkService
    }
    
    func getPulls(then completion: @escaping ([Issue]?) -> Void) {
        guard let accessToken = keyChainService.load(service: "access-token", account: "github") else {
            Log.error("Access Token is not found")
            return completion(nil)
        }
        
        guard let url = URL(string: Self.issueURL) else {
            Log.error("User API URL is wrong")
            return completion(nil)
        }
        
        let request = URLRequest(url: url, with: accessToken)
        
        networkService.get(request: request, then: completion)
    }
}

