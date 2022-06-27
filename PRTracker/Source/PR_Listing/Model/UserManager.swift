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
        
        let userResource = UserResource()
        let userRequest = APIRequest(resource: userResource, token: accessToken)
        
        networkService.execute(userRequest) { result in
            switch result {
            case .success(let data):
                completion(data)
            case .failure(let error):
                Log.error(error.localizedDescription + "(\(#file), \(#function), \(#line))")
                completion(nil)
            }
        }
    }
}
