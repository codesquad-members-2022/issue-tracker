//
//  TabBarController.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/14.
//

import UIKit

class TabBarController: UITabBarController {
    
    let issueViewController = IssueViewController()
    let labelViewController = LabelViewController()
    let milestoneViewController = MilestoneViewController()
    let myAccountViewController = MyAccountViewController()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setTabBarItems()
        setTabBarBackgroundColor()
    }
}

private extension TabBarController {
    
    func setTabBarItems() {
        let navigationViewController = UINavigationController(rootViewController: issueViewController)
        
        navigationViewController.tabBarItem = UITabBarItem(title: "이슈", image: UIImage(named: "alertcircle"), tag: 0)
        labelViewController.tabBarItem = UITabBarItem(title: "레이블", image: UIImage(named: "tag"), tag: 1)
        milestoneViewController.tabBarItem = UITabBarItem(title: "마일스톤", image: UIImage(named: "milestone"), tag: 2)
        myAccountViewController.tabBarItem = UITabBarItem(title: "내 계정", image: UIImage(named: "userimagesmall"), tag: 3)
        
        self.viewControllers = [navigationViewController, labelViewController, milestoneViewController, myAccountViewController]
    }
    
    func setTabBarBackgroundColor() {
        if #available(iOS 15.0, *) {
            let appearance = UITabBarAppearance()
            appearance.configureWithOpaqueBackground()
            appearance.backgroundColor = .systemGray6
            self.tabBar.standardAppearance = appearance
            self.tabBar.scrollEdgeAppearance = self.tabBar.standardAppearance
        } else {
            self.tabBar.barTintColor = .systemGray6
        }
    }
}
