//
//  TabBarViewController.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/14.
//

import UIKit

final class TabBarViewController: UITabBarController {

    override func viewDidLoad() {
        super.viewDidLoad()

        self.view.backgroundColor = .white

        let issueListViewController = IssueListViewController(viewModel: IssueListViewModel())
        // TODO: 다른 VC로 변경 예정
        let secondVC = UIViewController()
        let thirdVC = UIViewController()
        let fourthVC = UIViewController()

        let issueNavigationVC = UINavigationController(rootViewController: issueListViewController)

        issueNavigationVC.tabBarItem = UITabBarItem(title: "이슈", image: UIImage(named: "issueIcon"), selectedImage: UIImage(named: "issueIcon"))
        secondVC.tabBarItem = UITabBarItem(title: "레이블", image: UIImage(named: "tagIcon"), selectedImage: UIImage(named: "tagIcon"))
        thirdVC.tabBarItem = UITabBarItem(title: "마일스톤", image: UIImage(named: "milestoneIcon"), selectedImage: UIImage(named: "milestoneIcon"))
        fourthVC.tabBarItem = UITabBarItem(title: "내 계정", image: UIImage(named: "userImageIcon"), selectedImage: UIImage(named: "userImageIcon"))
        self.viewControllers = [issueNavigationVC, secondVC, thirdVC, fourthVC]

    }

}
