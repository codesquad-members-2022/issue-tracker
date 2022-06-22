//
//  ViewController.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/13.
//

import UIKit

class IssueViewController: UIViewController {

    private let leftButton: CustomBarButton = {
        let button = CustomBarButton()
        button.setConfiguration(title: "filter", imageSystemName: "line.3.horizontal.decrease", imagePlacement: .leading)
        return button
    }()

    private let rightButton: CustomBarButton = {
        let button = CustomBarButton()
        button.setConfiguration(title: "선택", imageSystemName: "checkmark.circle", imagePlacement: .trailing)
        return button
    }()

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
        let customButton = CustomBarButton()
        let leftBarButton = UIBarButtonItem(customView: customButton.leftButton)
        let rightBarButton = UIBarButtonItem(customView: customButton.rightButton)
        navigationItem.leftBarButtonItem = leftBarButton
        navigationItem.rightBarButtonItem = rightBarButton
        
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
