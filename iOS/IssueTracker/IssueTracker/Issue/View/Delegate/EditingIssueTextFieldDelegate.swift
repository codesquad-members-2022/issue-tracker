//
//  EditingIssueTextFieldDelegate.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/23.
//

import Foundation
import UIKit

final class EditingIssueTextFieldDelegate: NSObject, UITextFieldDelegate {
    func textFieldDidBeginEditing(_ textField: UITextField) {
        let userInfo: [String: String] = ["text": textField.text ?? ""]
        NotificationCenter.default.post(name: NotificationNames.textFieldDidBeginEditing,
                                        object: self,
                                        userInfo: userInfo)
    }
}

extension EditingIssueTextFieldDelegate {
    enum NotificationNames {
        static let textFieldDidBeginEditing = Notification.Name("textFieldDidBeginEditing")
    }
}
