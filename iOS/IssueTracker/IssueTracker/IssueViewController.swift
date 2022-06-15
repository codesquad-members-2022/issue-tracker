//
//  ViewController.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/13.
//

import UIKit

class IssueViewController: UIViewController {
    
    private var issueCollectionView = IssueCollectionView()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        createNavigationBarButton()
        self.view = issueCollectionView
        
    }
    
    private func createNavigationBarButton() {
        
        let leftButton: UIButton = {
            var config = UIButton.Configuration.plain()
            config.image = UIImage(named: "filter")
            config.imagePadding = 10
            var titleAttr = AttributedString.init("필터")
            titleAttr.font = .systemFont(ofSize: 17.0, weight: .light)
            config.attributedTitle = titleAttr
            
            let leftButton = UIButton(configuration: config)
            return leftButton
        }()
        let leftBarButton = UIBarButtonItem(customView: leftButton)
        
        let rightButton: UIButton = {
            var config = UIButton.Configuration.plain()
            
            var titleAttr = AttributedString.init("선택")
            titleAttr.font = .systemFont(ofSize: 17.0, weight: .light)
            config.attributedTitle = titleAttr
            
            config.image = UIImage(systemName: "checkmark.circle")
            config.imagePlacement = NSDirectionalRectEdge.trailing
            config.imagePadding = 10
            
            let rightButton = UIButton(configuration: config)
            return rightButton
        }()
        let rightBarButton = UIBarButtonItem(customView: rightButton)
        
        self.navigationItem.leftBarButtonItem = leftBarButton
        self.navigationItem.rightBarButtonItem = rightBarButton
        
    }
    

}

