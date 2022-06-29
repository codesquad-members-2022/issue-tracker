//
//  IssueViewController.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/24.
//

import SnapKit
import UIKit

protocol IssueViewDelegate: AnyObject {
    func didClickFilterButton()
    func didClickCreateButton()
}

class IssueViewController: UIViewController {
    private let tableView = UITableView(frame: .zero, style: .plain)
    private let dataSource = TableviewDataSource()
    weak var delegate: IssueViewDelegate?

    private let leftButton: TextButton = {
        let button = TextButton()
        button.setSymbol(UIImage.filter)
        button.setTitle("필터", for: .normal)
        button.setTitleColor(UIColor.primary, for: .normal)
        return button
    }()

    private let rightButton: TextButton = {
        let button = TextButton()
        let symbolConfiguration = UIImage.SymbolConfiguration(pointSize: 14)
        button.setSymbol(UIImage(systemName: "checkmark.circle", withConfiguration: symbolConfiguration), on: .trailing)
        button.setTitle("선택", for: .normal)
        return button
    }()

    private let issueCreateButton: UIButton = {
        let button = UIButton()
        button.clipsToBounds = true
        button.layer.cornerRadius = 30
        button.backgroundColor = .primary
        button.tintColor = .white
        button.setImage(UIImage.plus, for: .normal)
        return button
    }()

    private let searchController = UISearchController()

    override func viewDidLoad() {
        super.viewDidLoad()
        layout()
        configureView()
        registerAction()
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    private func configureView() {
        view.backgroundColor = .white

        navigationItem.title = "이슈"
        navigationItem.leftBarButtonItem = .init(customView: leftButton)
        navigationItem.rightBarButtonItem = .init(customView: rightButton)
        navigationController?.navigationBar.prefersLargeTitles = true

        delegate = self
        tableView.dataSource = dataSource
        tableView.delegate = self
        tableView.register(IssueListCell.self, forCellReuseIdentifier: IssueListCell.identifier)
    }

    private func layout() {
        view.addSubview(tableView)
        view.addSubview(issueCreateButton)

        tableView.snp.makeConstraints {
            $0.top.bottom.leading.trailing.equalTo(view.safeAreaLayoutGuide)
        }

        issueCreateButton.snp.makeConstraints {
            $0.trailing.bottom.equalTo(view.safeAreaLayoutGuide).offset(-30)
            $0.width.height.equalTo(60)
        }
    }

    private func registerAction() {
        leftButton.addAction(.init(handler: { [weak self] _ in
            self?.delegate?.didClickFilterButton()
        }), for: .touchUpInside)

        issueCreateButton.addAction(.init(handler: { [weak self] _ in
            self?.delegate?.didClickCreateButton()
        }), for: .touchUpInside)
    }
}

extension IssueViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: false)
    }

    func scrollViewWillBeginDragging(_: UIScrollView) {
        navigationItem.searchController = searchController
    }
}

extension IssueViewController: IssueViewDelegate {
    func didClickFilterButton() {
        let nextViewController = FilterViewController()
        let navigationBar = UINavigationController()
        navigationBar.viewControllers = [nextViewController]
        present(navigationBar, animated: true)
    }

    func didClickCreateButton() {
        let nextViewController = IssueCreateViewController()
        navigationController?.pushViewController(nextViewController, animated: true)
    }
}
