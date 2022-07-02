//
//  MainTabBarController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import UIKit

class MainTabBarController: UITabBarController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let issueVC = IssueListTableViewController()
        let labelVC = LabelViewController()
        
        let issueNavVC = IssueNavigationController(rootViewController: issueVC)
        issueNavVC.tabBarItem.title = "이슈"
        issueNavVC.tabBarItem.image = UIImage(systemName: "exclamationmark.circle")
        
        let labelNavVC = LabelNavigationController(rootViewController: labelVC)
        labelNavVC.tabBarItem.title = "레이블"
        labelNavVC.tabBarItem.image = UIImage(systemName: "tag.fill")
        
        viewControllers = [issueNavVC, labelNavVC]
    }
}
