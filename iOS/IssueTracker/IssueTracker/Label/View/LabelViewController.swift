//
//  LabelViewController.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/14.
//

import UIKit

final class LabelViewController: UIViewController {
    private let navigationItems = LabelViewNavigationItems()
    private lazy var labelTableView = LabelTableView(frame: view.frame)

    
    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationItems()
        view = labelTableView
        setTableView()
        
    }
}

private extension LabelViewController {
    func setNavigationItems() {
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: navigationItems.addButton)

        navigationItem.title = "레이블"
        navigationController?.navigationBar.prefersLargeTitles = true

        let appearance = UINavigationBarAppearance()
        navigationController?.navigationBar.standardAppearance = appearance
        navigationController?.navigationBar.scrollEdgeAppearance = appearance
    }

    func setTableView() {
        labelTableView.dataSource = dataSource
        labelTableView.estimatedRowHeight = 118
        labelTableView.rowHeight = UITableView.automaticDimension
    }
}
