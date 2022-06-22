//
//  AppleLoginUseCase.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/20.
//

struct AppleLoginUseCase: UseCaseResponsible {
    
    private let model = AppleLoginModel()
    
    func request(_ completionBlock: @escaping (Any?) -> Void) {
        guard let url = model.getLoginURL() else { return }
        completionBlock(url)
    }
}
