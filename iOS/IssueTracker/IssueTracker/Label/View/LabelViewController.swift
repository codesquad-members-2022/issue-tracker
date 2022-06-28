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
    private let viewModel: LabelViewModelProtocol

    private let dataSource: LabelTableDataSource

    init(viewModel: LabelViewModelProtocol) {
        self.viewModel = viewModel
        self.dataSource = LabelTableDataSource(labelItemData: viewModel.labels)
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationItems()
        view = labelTableView
        setTableView()
        bind(to: viewModel)
        viewModel.viewDidLoad()
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

    func bind(to viewModel: LabelViewModelProtocol) {
        viewModel.labels.bind(on: self) { [weak self] _ in
            self?.reloadTableView()
        }
        viewModel.selectedLabelIndex.bind(on: self) { [weak self] index in
            let labels = self?.viewModel.labels
            let label = labels?.value[index]
            self?.showEditLabelView(with: label)
        }
        viewModel.addButtonState.bind(on: self) { [weak self] _ in
            self?.showEditLabelView()
        }
        viewModel.error.bind(on: self) { [weak self] errorMessage in
            self?.showAlert(of: errorMessage)
        }

        navigationItems.addButton.addAction(UIAction { _ in
            viewModel.didTouchAddButton()
        }, for: .touchUpInside)
    }

    func reloadTableView() {
        DispatchQueue.main.async { [weak self] in
            self?.labelTableView.reloadData()
        }
    }

    func showEditLabelView(with label: LabelItem? = nil) {
        
    }

    func showAlert(of errorMessage: String) {
        DispatchQueue.main.async { [weak self] in
            let alert = UIAlertController(title: "오류가 발생했습니다.", message: errorMessage, preferredStyle: .alert)
            let alertAction = UIAlertAction(title: "OK", style: .cancel)
            alert.addAction(alertAction)
            self?.present(alert, animated: true)
        }
    }
}
