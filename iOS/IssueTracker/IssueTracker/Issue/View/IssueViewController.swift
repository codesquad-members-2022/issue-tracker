//
//  ViewController.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/13.
//

import UIKit

class IssueViewController: UIViewController {
    
    private lazy var issueCollectionView = IssueCollectionView(frame: view.frame)
    private var dataSource = IssueCollectionViewDataSource()
    private var searchController = UISearchController(searchResultsController: nil)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        createNavigationBarButton()
        createSearchController()
        self.view = issueCollectionView
        issueCollectionView.setDataSource(dataSource)
        issueCollectionView.collectionView.delegate = self
    }
    
    private func createNavigationBarButton() {
        let customButton = CustomBarButton()
        let leftBarButton = UIBarButtonItem(customView: customButton.leftButton)
        let rightBarButton = UIBarButtonItem(customView: customButton.rightButton)
        self.navigationItem.leftBarButtonItem = leftBarButton
        self.navigationItem.rightBarButtonItem = rightBarButton
    }
    
    private func createSearchController() {
        searchController.searchBar.placeholder = "Search"
        searchController.automaticallyShowsCancelButton = false
        searchController.hidesNavigationBarDuringPresentation = false
        searchController.obscuresBackgroundDuringPresentation = false
        
        let appearance = UINavigationBarAppearance()
        navigationController?.navigationBar.standardAppearance = appearance
        navigationController?.navigationBar.scrollEdgeAppearance = appearance
        
        self.navigationItem.title = "이슈"
        self.navigationController?.navigationBar.prefersLargeTitles = true
        self.navigationItem.largeTitleDisplayMode = .always
        self.navigationController?.hidesBarsOnSwipe = true
        self.navigationItem.searchController = searchController
    }
    
}

extension IssueViewController: UICollectionViewDelegate {

    func scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>) {
        UIView.animate(withDuration: 0.5) { [weak self] in
            guard velocity.y != 0 else { return }
            if velocity.y < 0 {
                let height = self?.searchController.searchBar.frame.height ?? 0.0
                self?.searchController.searchBar.alpha = 1.0
                self?.searchController.searchBar.frame.origin = CGPoint(x: 0, y: UIScreen.main.bounds.maxY - height)
            } else {
            self?.searchController.searchBar.alpha = 0.0
                self?.searchController.searchBar.frame.origin = CGPoint(x: 0, y: UIScreen.main.bounds.maxY)
            }
        }
    }
}
