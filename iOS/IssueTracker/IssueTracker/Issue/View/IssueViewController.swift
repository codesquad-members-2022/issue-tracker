//
//  ViewController.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/13.
//

import UIKit

class IssueViewController: UIViewController {
    private let navigationItems = IssueViewNavigationItems()
    private lazy var issueListView = IssueListView(frame: view.frame)
    private let dataSource: IssueListDataSource
    private var searchController = UISearchController(searchResultsController: nil)
    
    let issueViewModel: IssueViewModel
    
    init(issueViewModel: IssueViewModel) {
        self.issueViewModel = issueViewModel
        self.dataSource = IssueListDataSource(issueEntityList: issueViewModel.list)
        super.init(nibName: nil, bundle: nil)
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationItems()
        setSearchController()
        view = issueListView
        setIssueListView()
        bind()
        issueViewModel.loadFromIssueManager()
    }

    private func setNavigationItems() {
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: navigationItems.filterButton)
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: navigationItems.selectButton)
        
        navigationItem.title = "이슈"
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationItem.largeTitleDisplayMode = .always

        let appearance = UINavigationBarAppearance()
        navigationController?.navigationBar.standardAppearance = appearance
        navigationController?.navigationBar.scrollEdgeAppearance = appearance
    }
    
    private func setSearchController() {
        searchController.searchBar.placeholder = "Search"
        searchController.automaticallyShowsCancelButton = false
        searchController.hidesNavigationBarDuringPresentation = false
        searchController.obscuresBackgroundDuringPresentation = false
    }

    private func setIssueListView() {
        issueListView.dataSource = dataSource
        issueListView.delegate = self
    }
}

private extension IssueViewController/*: UICollectionViewDelegate*/ {
    func bind() {
        issueViewModel.list.bind(on: self) { [weak self] _ in
            DispatchQueue.main.async {
                self?.issueListView.reloadData()
            }
        }

        issueListView.addIssueButtonAddAction(UIAction { [weak self] _ in
            let editingIssueManager = EditingIssueManager()
            let editingIssueViewModel = EditingIssueViewModel(useCase: editingIssueManager)
            let editingIssueViewController = EditingIssueViewController(viewModel: editingIssueViewModel)
            self?.navigationController?.pushViewController(editingIssueViewController, animated: true)
        }, for: .touchUpInside)
    }

    func showEditingIssueView() {
        let editingIssueManager = EditingIssueManager()
        let editingIssueViewModel = EditingIssueViewModel(useCase: editingIssueManager)
        let editingIssueViewController = EditingIssueViewController(viewModel: editingIssueViewModel)
        navigationController?.pushViewController(editingIssueViewController, animated: true)
    }
}

extension IssueViewController: UITableViewDelegate {
    func scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>) {
        guard velocity.y != 0 else { return }

        if velocity.y < 0 {
            navigationItem.searchController = searchController
        } else {
            navigationItem.searchController = nil
        }
    }
}
