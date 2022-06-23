//
//  IssueFilterItemSelectViewController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/22.
//

import UIKit

class IssueFilterItemSelectViewController: UIViewController {
    // IssueFilterItemSelectViewController은 IssueListViewController에 의해 binding 되므로 ViewBindable 을 구현하는 ViewController 가 되었습니다.
    
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
