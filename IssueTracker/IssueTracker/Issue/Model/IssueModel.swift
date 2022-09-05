//
//  IssueModel.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/21.
//

import Foundation

class IssueModel {

    private let environment: IssueModelEnvironment
    
//    init(service: IssueService, repo: Repository) {
//        self.service = service
//        self.repo = repo
//    }
    
    init(environment: IssueModelEnvironment) {
        self.environment = environment
    }
    
    var issuesUpdated: ( () -> Void )?
    
    private var issues: [Issue] = [] {
        didSet {
            issuesUpdated?()
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
    
    func requestIssue(completion: @escaping ([String]?) -> Void) {
        environment.requestRepositoryIssues() { result in
            switch result {
            case .success(let issues):
                // MARK: 새 이슈 생성 후 돌아왔을 때 다시 요청하면, 새로 생성된 이슈가 오지 않음 but 포스트맨에서는 나옴 - 조금 시간이 걸리는 듯.
                print("요청 결과 : 성공, 이슈목록 : \(issues)")
                self.issues = issues
                let issuesTitleArr = issues.map{ $0.title }
                print(issuesTitleArr)
                completion(issuesTitleArr)
            case .failure(let error):
                print(error.localizedDescription)
                completion(nil)
            }
        }
        
//        var isSuccess = false
//        environment.requestRepositoryIssues() { result in
//            switch result {
//            case .success(let issues):
//                self.issues = issues
//                isSuccess = true
//            case .failure(let error):
//                print(error.localizedDescription)
//                completion(false)
//            }
//        }
//        return isSuccess // 무조건 false : 비동기 응답이 오기 전에 isSuccess를 반환하기 때문
    }
}

struct IssueModelEnvironment {
    // completion을 파라미터로 받고, 리턴타입은 Void인 클로저
    let requestRepositoryIssues: (@escaping (Result<[Issue], IssueError>) -> Void) -> Void
}
