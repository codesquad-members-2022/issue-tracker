//
//  IssueFilterItemSelectViewController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/22.
//

import UIKit

enum IssueFilterConstraint {
    /// 내가 보기에 열린 이슈
    case opned
    /// 내가 보기에 내가 작성한 이슈
    case written
    /// 내가 보기에 내게 할당된 이슈
    case assigned
    /// 내가 보기에 내가 댓글을 남긴 이슈
    case forReplied
    /// 내가 보기에 닫힌 이슈
    case closed
}

class IssueFilterItemSelectViewController: UIViewController, ViewBindable {
    // MARK: - ViewBindable Implements. Select Multiple Issue List Functionality.
    
    var vc: ViewBinding?
    
    func sendAction(_ param: Any?) {
        
    }
    
    func receive(_ responseData: Any) {
        
    }
    
    // MARK: - ViewController Functionality
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .red
    }
}
