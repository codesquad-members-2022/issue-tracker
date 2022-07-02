//
//  IssueModifyUseCase.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/29.
//

import Foundation
import Combine

class IssueModifyUseCase: UseCaseResponsible {
    
    private let updateModel = IssueUpdateModel()
    var param: Any? // request 함수에 파라미터로 넣고 싶었지만 팀원과 동시에 진행해야 하므로 지금은 이렇게 처리합니다.
    private var cancellables = Set<AnyCancellable>()
    
    func request(param: Any?, _ completionBlock: @escaping (Any?) -> Void) {
        updateModel
            .requestCloseIssue()?
            .subscribe(on: DispatchQueue.global())
            .sink(receiveValue: { response in
                switch response.result {
                case .success(let dto):
                    completionBlock(dto)
                case .failure(let error):
                    print(error)
                    completionBlock(error)
                }
            })
            .store(in: &cancellables)
    }
}
