//
//  IssueService.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/21.
//

import Foundation
import Alamofire


enum IssueError: Error {
    case issueNotFound
    case cannotCreateIssue
}

struct IssueService {
    
    func requestIssues(accessToken: String, completion: @escaping (Result<[Issue], IssueError>) -> Void) {
        let urlString = RequestURL.issues.description
        let headers: HTTPHeaders = [
            NetworkHeader.acceptV3.getHttpHeader(),
            NetworkHeader.authorization(accessToken: accessToken).getHttpHeader()
        ]
        
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        
        let globalThread = DispatchQueue.global(qos: .default)
        AF.request(urlString,
                   method: .get,
                   headers: headers)
            .responseDecodable(of: [Issue].self,
                               queue: globalThread,
                               decoder: decoder) { (response) in
            switch response.result {
            case let .success(decodeData):
                completion(.success(decodeData))
            case .failure:
                completion(.failure(.issueNotFound))
            }
        }
    }
    
    func createIssue(title: String, repo: Repository, accessToken: String, completion: @escaping (Bool) -> Void) {
        let urlString = RequestURL.createIssue(owner: repo.owner.login, repo: repo.name).description
        let headers: HTTPHeaders = [
            NetworkHeader.acceptV3.getHttpHeader(),
            NetworkHeader.authorization(accessToken: accessToken).getHttpHeader()
        ]
        let parameters: [String: Any] = [
            "title": title,
            // 로그인 한 유저를 담당자로 해야 한다
//            "assignees": [repo.owner.login]
        ]
        
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        
        AF.request(urlString,
                   method: .post,
                   parameters: parameters,
                   encoding: JSONEncoding.default,
                   headers: headers)
            .response { response in
                switch response.result {
                case let .success:
                    completion(true)
                case .failure:
                    completion(false)
                }
            }
    }
    
    func requestRepositoryIssues(accessToken: String, repo: Repository, completion: @escaping (Result<[Issue], IssueError>) -> Void) {
        let urlString = RequestURL.createIssue(owner: repo.owner.login, repo: repo.name).description
        let headers: HTTPHeaders = [
            NetworkHeader.acceptV3.getHttpHeader(),
            NetworkHeader.authorization(accessToken: accessToken).getHttpHeader()
        ]
        
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        
        AF.request(urlString, method: .get, headers: headers)
            .responseDecodable(of: [ResponseIssue].self, decoder: decoder) { response in
                switch response.result {
                case .success(let data):
                    var result: [Issue] = []
                    for entity in data {
                        // pullRequest 가 없으면(nil) 일반이슈, 있으면 PR 이슈
                        if entity.pullRequest != nil {
                            continue
                        }
                        let issue = Issue(title: entity.title, body: entity.body, state: entity.state, labels: entity.labels, milestone: entity.milestone, repository: repo)
                        result.append(issue)
                    }
                    completion(.success(result))
                case .failure(let error):
                    print(error)
                    completion(.failure(.issueNotFound))
                }
            }
    }
    
    func requestRepos(accessToken: String, completion: @escaping (Result<[Repository], IssueError>) -> Void) {
        let urlString = RequestURL.repos.description
        let headers: HTTPHeaders = [
            NetworkHeader.acceptV3.getHttpHeader(),
            NetworkHeader.authorization(accessToken: accessToken).getHttpHeader()
        ]
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        
        AF.request(urlString, method: .get, headers: headers)
            .responseDecodable(of: [Repository].self, decoder: decoder) { response in
                switch response.result {
                case .success(let data):
                    completion(.success(data))
                case .failure:
                    completion(.failure(.issueNotFound))
                }
            }
    }
    
    struct ResponseIssue: Codable {
        let title: String
        let body: String?
        let state: String
        let labels: [Label]
        let milestone: Milestone?
        let pullRequest: PullRequest?
        
        struct PullRequest: Codable {
            let url: String
            let htmlUrl: String
            let diffUrl: String
            let patchUrl: String
            let mergedAt: String?
        }
    }
}
