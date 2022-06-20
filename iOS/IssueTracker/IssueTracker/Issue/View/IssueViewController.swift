//
//  ViewController.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/13.
//

import UIKit

class IssueViewController: UIViewController {
    
    private lazy var issueCollectionView = IssueCollectionView(frame: view.frame)
    private var dataSource = IssueCollectionViewDataSource()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        createNavigationBarButton()
        self.view = issueCollectionView
        issueCollectionView.setDataSource(dataSource)
    }
    
    private func createNavigationBarButton() {
        let customButton = CustomBarButton()
        let leftBarButton = UIBarButtonItem(customView: customButton.leftButton)
        let rightBarButton = UIBarButtonItem(customView: customButton.rightButton)
        self.navigationItem.leftBarButtonItem = leftBarButton
        self.navigationItem.rightBarButtonItem = rightBarButton
    }
    
}
