//
//  LabelViewController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import UIKit

final class LabelViewController: UITableViewController {
    
    private lazy var addButton: UIBarButtonItem = {
        let barButtonItem = UIBarButtonItem(barButtonSystemItem: .add, target: self, action: #selector(showAddLabelView))
        return barButtonItem
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUp()
        self.navigationItem.rightBarButtonItem = addButton
    }
    
    private func setUp() {
        self.navigationItem.title = "레이블"
    }
    
    @objc func showAddLabelView() {
        
    }
    
}
