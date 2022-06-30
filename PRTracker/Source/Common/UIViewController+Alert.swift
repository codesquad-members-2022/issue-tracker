//
//  UIViewController+Alert.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/23.
//

import UIKit

extension UIViewController {
    func alert(title: String,
               message: String,
               okTitle: String,
               handler: ((UIAlertAction) -> Void)? = nil) {
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        
        let okAction = UIAlertAction(title: okTitle, style: .default, handler: handler)
        alert.addAction(okAction)
        
        present(alert, animated: true, completion: nil)
    }
}

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

