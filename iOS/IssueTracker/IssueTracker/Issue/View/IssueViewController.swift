//
//  ViewController.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/13.
//

import UIKit

class IssueViewController: UIViewController {
    private let navigationItems = IssueViewNavigationItems()
    private lazy var issueCollectionView = IssueCollectionView(frame: view.frame)
    private var dataSource: IssueCollectionViewDataSource
    private var searchController = UISearchController(searchResultsController: nil)
    
    let issueViewModel: IssueViewModel
    
    init(issueViewModel: IssueViewModel) {
        self.issueViewModel = issueViewModel
        self.dataSource = IssueCollectionViewDataSource(issueViewModel: issueViewModel)
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
        view = issueCollectionView

        issueCollectionView.setDataSource(dataSource)
        setButtonAction()
        issueCollectionView.setCollectionViewDelegate(self)
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

    private func setButtonAction() {
        issueCollectionView.setNewIssueButtonAction(UIAction { [weak self] _ in
            let editingIssueViewController = EditingIssueViewController()
            self?.navigationController?.pushViewController(editingIssueViewController, animated: true)
        })
    }
}

extension IssueViewController: UICollectionViewDelegate {
    func scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>) {
        guard velocity.y != 0 else { return }

        if velocity.y < 0 {
            navigationItem.searchController = searchController
        } else {
            navigationItem.searchController = nil
        }
    }
    
    func bind() {
        self.issueViewModel.list.bind(on: self) { _ in
            DispatchQueue.main.async {
                self.issueCollectionView.update()
            }
        }
    }
}
