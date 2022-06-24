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
    
    init(service: IssueService, token: String) {
        self.service = service
        self.accessToken = token
    }
    
    var updatedIssues: ( (_ issues: [Issue]) -> Void )?
    
    private var issues: [Issue] = [] {
        didSet { // issue내용 바뀌면 updatedIssues를 호출 : delegate역할
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
        service.requestIssues(accessToken: accessToken) { result in
            switch result {
            case .success(let issues):
                self.issues = issues
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
}
