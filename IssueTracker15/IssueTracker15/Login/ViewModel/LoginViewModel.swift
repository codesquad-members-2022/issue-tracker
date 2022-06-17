//
//  LoginViewModel.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/16.
//

import Foundation

final class LoginViewModel: CommonViewModel {
    var output: (Any?, ViewBindable) -> Void
    
    init(_ output: @escaping (Any?, ViewBindable) -> Void) {
        self.output = output
    }
    
    func request(_ bindable: ViewBindable, param: Any?) {
        self.output(param, bindable)
        print("request!")
    }
}
