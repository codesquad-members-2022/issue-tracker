//
//  LabelViewController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import UIKit

final class LabelViewController: UITableViewController, ViewBinding {
    
    private var addButton = AddLabelBarButtonItem()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUp()   
    }
    
    func inputViewEvent(_ target: ViewBindable, _ param: Any?) {
        print("HI!")
    }
    
    private func setUp() {
        self.navigationItem.title = "레이블"
        self.navigationItem.rightBarButtonItem = addButton
        self.addButton.setVC(self)
    }
}
