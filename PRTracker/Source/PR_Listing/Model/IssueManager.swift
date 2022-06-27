//
//  PullManager.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

protocol IssueService {
    func getIssues(then completion: @escaping ([Issue]?) -> Void)
}

struct IssueManager: IssueService {
    
    let keyChainService: KeyChainService
    let networkService: NetworkService
    
    init(keyChainService: KeyChainService = KeyChainManager(),
         networkService: NetworkService = NetworkManger()) {
        self.keyChainService = keyChainService
        self.networkService = networkService
    }
    
    func getIssues(then completion: @escaping ([Issue]?) -> Void) {
        guard let accessToken = keyChainService.load(service: "access-token", account: "github") else {
            Log.error("Access Token is not found")
            return completion(nil)
        }
        
        let issuesResource = IssuesResource(state: .all, isPull: true)
        let issuesRequest = APIRequest(resource: issuesResource, token: accessToken)
        
        issuesRequest.execute { result in
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

