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

    private let leftButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: "line.3.horizontal.decrease.circle"), for: .normal)
        button.setTitle("필터", for: .normal)
        button.setTitleColor(UIColor.primary, for: .normal)
        return button
    }()
    
    private let rightButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: "checkmark.circle"), for: .normal)
        button.setTitle("선택", for: .normal)
        button.setTitleColor(UIColor.primary, for: .normal)
        button.semanticContentAttribute = .forceRightToLeft
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
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: leftButton)
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: rightButton)
        navigationController?.navigationBar.prefersLargeTitles = true

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
