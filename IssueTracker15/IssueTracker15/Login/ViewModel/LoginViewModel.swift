//
//  LoginViewModel.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/16.
//

import Foundation

struct LoginViewModel: CommonViewModel {
    var output: (Any?, ViewBindable) -> Void
    
    private var githubLoginUseCase: UseCaseResponsible = GithubLoginUseCase()
    private var appleLoginUseCase: UseCaseResponsible = AppleLoginUseCase()
    
    init(_ output: @escaping (Any?, ViewBindable) -> Void) {
        self.output = output
    }
    
    func request(_ bindable: ViewBindable, param: Any?) {
        guard let loginType = param as? LoginType else { return }
        
        switch loginType {
        case .gitHub:
            githubLoginUseCase.requestFromUseCase { loginURL in
                self.output(loginURL, bindable)
            }
        case .apple:
            appleLoginUseCase.requestFromUseCase { loginURL in
                self.output(loginURL, bindable)
            }
        }
    }
}
