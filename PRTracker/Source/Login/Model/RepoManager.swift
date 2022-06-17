//
//  RepoManager.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

struct RepoManager {
    
    let keyChainService: KeyChainService
    let networkService: NetworkService
    
    init(keyChainService: KeyChainService = KeyChainManager(),
         networkService: NetworkService = NetworkManger()) {
        self.keyChainService = keyChainService
        self.networkService = networkService
    }
    
    func getRepos(of user: User, then completion: @escaping ([Repo]?) -> Void) {
        guard let accessToken = keyChainService.load(service: "access-token", account: "github") else {
            Log.error("Access Token is not found")
            return completion(nil)
        }
        
        guard let repoURLString = user.reposURL, let url = URL(string: repoURLString) else {
            Log.error("User API URL is wrong")
            return completion(nil)
        }
        
        var request = URLRequest(url: url)
        request.setValue("application/json", forHTTPHeaderField: "Accept")
        request.setValue("token \(accessToken)", forHTTPHeaderField: "Authorization")
        
        networkService.get(request: request, then: completion)
    }
}
