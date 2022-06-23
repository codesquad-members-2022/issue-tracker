//
//  IssueFilterItemSelectViewController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/22.
//

import UIKit

class IssueFilterItemSelectViewController: UIViewController, ViewBindable {
    // MARK: - ViewBindable Implements. Select Multiple Issue List Functionality.
    
    var vc: ViewBinding?
    
    func sendAction(_ param: Any?) {
        
    }
    
    func receive(_ responseData: Any) {
        
    }
    
    func setVC(_ binding: ViewBinding) {
        vc = binding
    }
    
    // MARK: - ViewController Functionality
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .red
    }
}
