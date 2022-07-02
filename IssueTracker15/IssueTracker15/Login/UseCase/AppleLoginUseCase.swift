//
//  AppleLoginUseCase.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/20.
//

struct AppleLoginUseCase: UseCaseResponsible {
    
    private let model: LoginURLCreator?
    
    init(model: LoginURLCreator) {
        self.model = model
    }
    
    func request(param: Any?, _ completionBlock: @escaping (Any?) -> Void) {
        guard let url = model?.getLoginURL() else { return }
        completionBlock(url)
    }
}
