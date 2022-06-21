//
//  UIApplicationStub.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/21.
//

import Foundation
import UIKit

class UIApplicationStub: UIApplication {
    
    var openedURL: URL?
    
    override func open(_ url: URL, options: [UIApplication.OpenExternalURLOptionsKey: Any] = [:], completionHandler completion: ((Bool) -> Void)? = nil) {
        self.openedURL = url
    }
}
