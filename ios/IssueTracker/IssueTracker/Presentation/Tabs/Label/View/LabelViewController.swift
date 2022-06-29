//
//  LabelViewController.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

final class LabelViewController: UIViewController {
    private lazy var addButton: UIButton = {
        let symbolConfiguration = UIImage.SymbolConfiguration(pointSize: 14)
        let image = UIImage(systemName: "plus", withConfiguration: symbolConfiguration)
        let button = TextButton()

        button.setTitle("추가", for: .normal)
        button.setSymbol(image, on: .trailing)
        button.addAction(.init(handler: self.showLabelAdditionModal), for: .touchUpInside)

        return button
    }()

    private let tableView = UITableView()

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }

    private func configureUI() {
        view.backgroundColor = .systemBackground
        configureNavigationBar()
        configureTableView()
    }

    private func configureNavigationBar() {
        navigationItem.title = "레이블"
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationItem.rightBarButtonItem = .init(customView: addButton)
    }

    private func configureTableView() {
        view.addSubview(tableView)
        tableView.frame = view.frame
        tableView.autoresizingMask = [
            .flexibleBottomMargin,
            .flexibleLeftMargin,
            .flexibleRightMargin,
            .flexibleTopMargin,
            .flexibleWidth,
            .flexibleHeight
        ]

        tableView.dataSource = self
        tableView.register(LabelCell.self, forCellReuseIdentifier: LabelCell.reuseIdentifier)
    }

    deinit {
        print("Deinit: \(#fileID)")
    }
}

// MARK: - Table view data source
extension LabelViewController: UITableViewDataSource {
    func tableView(_: UITableView, numberOfRowsInSection _: Int) -> Int {
        12
    }

    func tableView(_ tableView: UITableView, cellForRowAt _: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LabelCell.reuseIdentifier) as? LabelCell else {
            return UITableViewCell()
        }

        cell.setTitle("Documentation", color: .secondary3)
        cell.setDescription("개발 내용을 문서화 한 이슈에 사용하는 레이블")

        return cell
    }
}

// MARK: - Action handlers
extension LabelViewController {
    func showLabelAdditionModal(_: UIAction) {
        // TODO: Show new view controller modally from view model
    }
}
