//
//  IssueListView.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/30.
//

import UIKit

final class IssueListView: UIView {
    private let tableView: UITableView = {
        let tableView = UITableView()
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.register(IssueListCell.self, forCellReuseIdentifier: IssueListCell.identifier)
        return tableView
    }()

    private let addIssueButton = HamburgerButton()

    var dataSource: UITableViewDataSource? {
        get {
            return tableView.dataSource
        } set {
            tableView.dataSource = newValue
        }
    }

    var delegate: UITableViewDelegate? {
        get {
            return tableView.delegate
        } set {
            tableView.delegate = newValue
        }
    }

    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .white
        setSubviewsLayout()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func reloadData() {
        tableView.reloadData()
    }

    func addIssueButtonAddAction(_ action: UIAction, for event: UIControl.Event) {
        addIssueButton.addAction(action, for: event)
    }
}

private extension IssueListView {
    func setSubviewsLayout() {
        addSubviews(tableView, addIssueButton)
        let screenSize = UIScreen.main.bounds.size
        tableView.snp.makeConstraints { make in
            make.edges.equalToSuperview()
        }

        addIssueButton.setConfiguration(systemImageName: "plus",
                                        backgroundColor: .primary1)
        addIssueButton.snp.makeConstraints { make in
            make.trailing.equalTo(safeAreaLayoutGuide).inset(20)
            make.bottom.equalTo(safeAreaLayoutGuide).inset(20)
            make.height.equalTo(64/375 * screenSize.width)
            make.width.equalTo(addIssueButton.snp.height)
        }
    }
}
