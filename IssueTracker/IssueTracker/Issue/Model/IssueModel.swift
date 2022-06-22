//
//  IssueModel.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/21.
//

import Foundation

class IssueModel {

    private let githubService = GitHubService()
    
    var updatedIssues: (_ issues: [Issue]) -> Void = { issues in
        
    }
    
    private var issues: [Issue] = [] {
        didSet { // issue내용 바뀌면 updatedIssues를 호출 : delegate역할
            updatedIssues(issues)
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
        guard let token = GithubUserDefaults.getToken() else {
            return
        }
        githubService.requestIssues(accessToken: token) { result in
            switch result {
            case .success(let issues):
                self.issues = issues
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
}
