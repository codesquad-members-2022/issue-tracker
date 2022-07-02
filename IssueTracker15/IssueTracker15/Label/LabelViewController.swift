//
//  LabelViewController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import UIKit

final class LabelViewController: UIViewController, ViewBinding {
    
    private lazy var addButton = AddLabelBarButtonItem()
    private lazy var dataSource = IssueLabelDataSource()
    private lazy var tableView: UITableView = {
        let tableView = UITableView(frame: .zero, style: .plain)
        tableView.dataSource = self.dataSource
        tableView.register(LabelTableViewCell.self, forCellReuseIdentifier: LabelTableViewCell.ID)
        return tableView
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setUp()
    }
    
    func inputViewEvent(_ target: ViewBindable, _ param: Any?) {
        self.present(AddLabelViewController(), animated: true)
    }
    
    private func setUp() {
        setNavigationItem()
        setTableView()
    }
    
    private func setNavigationItem() {
        self.navigationItem.title = "레이블"
        self.navigationItem.rightBarButtonItem = addButton
        self.addButton.setVC(self)
    }
    
    private func setTableView() {
        self.view.addSubview(tableView)
        tableView.snp.makeConstraints {
            $0.edges.equalToSuperview()
        }
    }
}
