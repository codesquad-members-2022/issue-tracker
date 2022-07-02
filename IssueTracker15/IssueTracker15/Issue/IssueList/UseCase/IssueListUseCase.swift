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
    private var cancellables = Set<AnyCancellable>()
    
    func request(param: Any?, _ completionBlock: @escaping (Any?) -> Void) {
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
