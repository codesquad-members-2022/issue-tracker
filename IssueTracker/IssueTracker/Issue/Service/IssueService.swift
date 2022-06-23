//
//  IssueService.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/21.
//

import Foundation
import Alamofire

class IssueService {
    
    func requestIssues(accessToken: String, completion: @escaping (Result<[Issue], GitHubError>) -> Void) {
        let urlString = RequestURL.issues.description
        let headers: HTTPHeaders = [
            NetworkHeader.acceptV3.getHttpHeader(),
            NetworkHeader.authorization(accessToken: accessToken).getHttpHeader()
        ]
        
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        
        AF.request(urlString, method: .get, headers: headers)
            .responseDecodable(of: [Issue].self, decoder: decoder) { (response) in
            switch response.result {
            case let .success(decodeData):
                completion(.success(decodeData))
            case .failure:
                completion(.failure(.issueNotFound))
            }
        }
    }
    
    // 생성되었다면, statusCode: 201, body 에 생성된 Issue 정보를 객체로 보내준다
    func createIssue(title: String, accessToken: String, completion: @escaping (Result<Issue, GitHubError>) -> Void) {
        let urlString = RequestURL.createIssue(owner: "Jinsujin", repo: "issue-tracker").description
        let headers: HTTPHeaders = [
            NetworkHeader.acceptV3.getHttpHeader(),
            NetworkHeader.authorization(accessToken: accessToken).getHttpHeader()
        ]
        let parameters: [String: Any] = [
            "title": title
        ]
        
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        
        AF.request(urlString, method: .post, parameters: parameters, encoding: JSONEncoding.default, headers: headers)
            .responseDecodable(of: Issue.self, decoder: decoder) { response in
                switch response.result {
                case let .success(decodeData):
                    completion(.success(decodeData))
                case .failure(let error):
                    completion(.failure(.cannotCreateIssue))
                }
            }
    }
}
