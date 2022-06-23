//
//  UIViewController+Alert.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/23.
//

import Foundation
import UIKit

extension UIAlertController {
    static func makeAlert(_ alertProvider: AlertConfiguration) -> UIAlertController {
        let alert = UIAlertController(title: alertProvider.title, message: alertProvider.message, preferredStyle: .alert)
        let action = UIAlertAction(title: "확인", style: .default, handler: alertProvider.handler)
        alert.addAction(action)
        return alert
    }
}

struct AlertConfiguration {
    let title: String
    let message: String
    let handler: ((UIAlertAction) -> Void)?
}

