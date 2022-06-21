//
//  LoginUseCase.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/20.
//

struct GithubLoginUseCase: UseCaseResponsible {
    
    private let model = GitHubLoginModel()
    
    func requestFromUseCase(_ completionBlock: @escaping (Any?) -> Void) {
        guard let url = model.getLoginURL() else { return }
        completionBlock(url)
    }
}
