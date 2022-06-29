//
//  IssueModel.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/21.
//

import Foundation

class IssueModel {

    private let service: IssueService
    private let accessToken: String
    private let repo: Repository
    
    init(service: IssueService, token: String, repo: Repository) {
        self.service = service
        self.accessToken = token
        self.repo = repo
    }
    
    var updatedIssues: ( (_ issues: [Issue]) -> Void )?
    
    private var issues: [Issue] = [] {
        didSet {
            updatedIssues?(issues)
        }
    }
    
    func getIssuesCount() -> Int {
        return issues.count
    }
    
    func getIssue(at index: Int) -> Issue? {
        if issues.indices.contains(index) {
            return issues[index]
        }
        return nil
    }
    
    func requestIssue() {
        service.requestRepositoryIssues(accessToken: accessToken, repo: repo) { result in
            switch result {
            case .success(let issues):
                self.issues = issues
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
}
