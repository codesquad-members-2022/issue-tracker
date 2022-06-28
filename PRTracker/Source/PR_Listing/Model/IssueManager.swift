//
//  PullManager.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

protocol IssueService {
    func getIssues(then completion: @escaping ([Issue]?) -> Void)
    func close(issueId: Int, completion: @escaping (Issue?) -> Void)
}

struct IssueManager: IssueService {
    
    let keyChainService: KeyChainService
    let networkService: NetworkService
    
    var issueStorage = Storage<Int, Issue>()
    
    init(keyChainService: KeyChainService = KeyChainManager(),
         networkService: NetworkService = NetworkManger()) {
        self.keyChainService = keyChainService
        self.networkService = networkService
    }
    
    func getAccessToken() -> String? {
        guard let accessToken = keyChainService.load(service: "access-token", account: "github") else {
            Log.error("Access Token is not found")
            return nil
        }
        return accessToken
    }
    
    func getIssues(then completion: @escaping ([Issue]?) -> Void) {
        guard let token = getAccessToken() else {
            return completion(nil)
        }
        
        let issuesResource = IssuesResource(state: .all, isPull: true)
        let issuesRequest = APIRequest(resource: issuesResource, token: token)
        
        networkService.execute(issuesRequest) { result in
            switch result {
            case .success(let issues):
                issues.forEach { issue in self.issueStorage.value[issue.id] = issue }
                completion(issues)
            case .failure(let error):
                Log.error(error.localizedDescription + "(\(#file), \(#function), \(#line))")
                completion(nil)
            }
        }
    }
    
    func close(issueId: Int, completion: @escaping (Issue?) -> Void) {
        guard let token = getAccessToken() else {
            return completion(nil)
        }
        
        guard let issue = issueStorage.value[issueId] else {
            Log.error("Wrong issue id")
            return completion(nil)
        }

        let body = "{\"state\":\"close\"}"
        
        let issueUpdate = IssueUpdateResource(issue: issue)
        let request = APIRequest(resource: issueUpdate, httpMethod: .patch, token: token, body: body)
        
        networkService.execute(request) { result in
            switch result {

            case .success(let issue):
                Log.info("\(issue)")
                completion(issue)
            case .failure(let error):
                Log.error(error.localizedDescription + "(\(#file), \(#function), \(#line))")
                completion(nil)
            }
        }
    }
    
    func closeIssue(at index: Int, completion: @escaping () -> Void) {
        completion()
    }
}

class Storage<Key: Hashable, Value> {
    var value: [Key: Value]
    
    init(value: [Key: Value] = [:]) {
        self.value = value
    }
}
