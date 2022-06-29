//
//  LoginViewModel.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/16.
//

import Foundation

struct LoginViewModel: CommonViewModel {
    var output: (Any?, ViewBindable) -> Void
    
    private var githubLoginUseCase = UseCaseContainer.shared.resolve(type: GithubLoginUseCase.self)
    private var appleLoginUseCase = UseCaseContainer.shared.resolve(type: AppleLoginUseCase.self)
    
    init(_ output: @escaping (Any?, ViewBindable) -> Void) {
        self.output = output
    }
    
    func request(_ bindable: ViewBindable, param: Any?) {
        guard let loginType = param as? LoginType else { return }
        
        switch loginType {
        case .gitHub:
            guard let useCase = githubLoginUseCase else { return }
            useCase.request { loginURL in
                self.output(loginURL, bindable)
            }
            
        case .apple:
            appleLoginUseCase?.request { loginURL in
                self.output(loginURL, bindable)
            }
        }
    }
}
