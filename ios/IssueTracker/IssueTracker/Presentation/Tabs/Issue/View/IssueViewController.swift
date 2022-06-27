//
//  IssueViewController.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/24.
//

import SnapKit
import UIKit

class IssueViewController: UIViewController {
    private let tableView = UITableView(frame: .zero, style: .plain)
    private let dataSource = TableviewDataSource()

    private let leftButton: UIBarButtonItem = {
        let button = UIBarButtonItem()
        button.image = UIImage(systemName: "line.3.horizontal.decrease.circle")
        button.tintColor = .primary
        return button
    }()

    private let rightButton: UIBarButtonItem = {
        let button = UIBarButtonItem()
        button.image = UIImage(systemName: "checkmark.circle")
        button.tintColor = .primary
        return button
    }()

    private let searchController: UISearchController = {
        let searchController = UISearchController()
        return searchController
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        layout()
        configureView()
        view.backgroundColor = .white
        navigationItem.leftBarButtonItem = leftButton
        navigationItem.rightBarButtonItem = rightButton
        navigationItem.title = "이슈"
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    private func configureView() {
        tableView.dataSource = dataSource
        tableView.register(IssueListCell.self, forCellReuseIdentifier: IssueListCell.identifier)
    }

    private func layout() {
        view.addSubview(tableView)

        tableView.snp.makeConstraints {
            $0.top.bottom.leading.trailing.equalTo(view.safeAreaLayoutGuide)
        }
    }
}
