//
//  LabelNavigationController.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/29.
//

import UIKit

final class LabelNavigationController: UINavigationController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUp()
    }
    
    private func setUp() {
        self.navigationBar.prefersLargeTitles = true
    }
}
