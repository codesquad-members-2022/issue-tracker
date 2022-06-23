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
    private var dataSource = IssueCollectionViewDataSource()
    private var searchController = UISearchController(searchResultsController: nil)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationItems()
        setSearchController()
        view = issueCollectionView

        issueCollectionView.setDataSource(dataSource)
        setButtonAction()
        issueCollectionView.setCollectionViewDelegate(self)
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
            let editingIssueManager = EditingIssueManager()
            let editingIssueViewModel = EditingIssueViewModel(useCase: editingIssueManager)
            let editingIssueViewController = EditingIssueViewController(viewModel: editingIssueViewModel)
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
}
