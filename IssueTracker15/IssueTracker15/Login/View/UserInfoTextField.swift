//
//  PasswordTextField.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/23.
//

import UIKit

final class UserInfoTextField: UITextField, ViewBindable {
    var vc: ViewBinding?
    private(set) var type:UserInputType = .ID
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.addTarget(self, action: #selector(textFieldDidChange), for: .editingChanged)
    }

    @available(*, unavailable) required init(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func sendAction(_ param: Any?) {
        vc?.inputViewEvent(self, nil)
    }
    
    func receive(_ responseData: Any) {
        print(responseData)
    }
    
    func setVC(_ binding: ViewBinding) {
        self.vc = binding
    }
    
    func setPlaceHolder(_ text: String) {
        self.placeholder = text
    }
    
    func setType(_ type: UserInputType) {
        self.type = type
    }
    
    @objc func textFieldDidChange(_ textField: UITextField) {
        sendAction(textField.text)
    }
}
