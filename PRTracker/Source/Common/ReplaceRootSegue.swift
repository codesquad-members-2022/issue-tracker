//
//  ReplaceRootSegue.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/15.
//

import UIKit

class ReplaceRootSegue: UIStoryboardSegue {
    override func perform() {
        var window: UIWindow?

        guard let sceneDelegate = UIApplication.shared.connectedScenes.first?.delegate as? SceneDelegate else { return }
        
        window = sceneDelegate.window

        window?.rootViewController?.view.removeFromSuperview()
        window?.rootViewController?.removeFromParent()
        window?.rootViewController = destination
        
        if let mainWindow = window {
            UIView.transition(with: mainWindow, duration: 0.3,
                              options: [.transitionCrossDissolve],
                              animations: nil,
                              completion: nil)
        }
    }
}
