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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        createNavigationBarButton()
        createSearchController()
        self.view = issueCollectionView
        issueCollectionView.setDataSource(dataSource)
        setButtonAction()
    }
    
    private func createNavigationBarButton() {
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(customView: leftButton)
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(customView: rightButton)
    }
    
    private func createSearchController() {
        let searchController = UISearchController(searchResultsController: nil)
        searchController.searchBar.placeholder = "Search"
        searchController.automaticallyShowsCancelButton = false
        searchController.hidesNavigationBarDuringPresentation = false
        searchController.obscuresBackgroundDuringPresentation = false
        
        self.navigationItem.title = "이슈"
        self.navigationController?.navigationBar.prefersLargeTitles = true
        self.navigationItem.largeTitleDisplayMode = .always
        self.navigationItem.searchController = searchController
    }

    private func setButtonAction() {
        issueCollectionView.setNewIssueButtonAction(UIAction { [weak self] _ in
            let editingIssueViewController = EditingIssueViewController()
            self?.navigationController?.pushViewController(editingIssueViewController, animated: true)
        })
    }
}
