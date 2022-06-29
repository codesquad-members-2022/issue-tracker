//
//  IssueListUseCase.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/29.
//

import Foundation
import Combine

class IssueListUseCase: UseCaseResponsible {
    
    private let listModel = IssueListModel()
    var param: Any? // request 함수에 파라미터로 넣고 싶었지만 팀원과 동시에 진행해야 하므로 지금은 이렇게 처리합니다.
    
    private var cancellables = Set<AnyCancellable>()
    
    func request(_ completionBlock: @escaping (Any?) -> Void) {
        listModel
            .requestListIssues()?
            .subscribe(on: DispatchQueue.global())
            .sink(receiveValue: { response in
                switch response.result {
                case .success(let result):
                    completionBlock(result)
                case .failure(let error):
                    completionBlock(error)
                }
            })
            .store(in: &cancellables)
    }
}
