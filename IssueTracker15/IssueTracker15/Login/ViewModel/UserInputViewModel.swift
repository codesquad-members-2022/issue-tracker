//
//  UserInputViewModel.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/23.
//

import Foundation

struct UserInputViewModel: CommonViewModel {
    var output: (Any?, ViewBindable) -> Void
    private var validateModel: ValidateModel = ValidateModel()
    
    init(_ output: @escaping (Any?, ViewBindable) -> Void) {
        self.output = output
    }
    
    func request(_ bindable: ViewBindable, param: Any?) {
        guard let userInputTexField = bindable as? UserInfoTextField,
              let userInput = param as? String
        else { return }
        let result = validateModel.validate(type: userInputTexField.type, text: userInput)
        output(result, bindable)
    }
}
