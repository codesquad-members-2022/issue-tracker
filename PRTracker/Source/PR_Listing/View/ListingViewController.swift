//
//  ListingViewController.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/15.
//

import UIKit

class ListingViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        configureSearchController()
        configureNavigationBar()
    }
    
    private func configureSearchController() {
        let searchController = UISearchController(searchResultsController: nil)
        searchController.searchBar.placeholder = "Search"
        searchController.searchResultsUpdater = self
        searchController.searchBar.delegate = self
        
        searchController.searchBar.showsCancelButton = false
        searchController.hidesNavigationBarDuringPresentation = false
        
        self.navigationItem.searchController = searchController
    }
    
    private func configureNavigationBar() {
        let button = CustomBarButton()
        let leftBarButtonItem = UIBarButtonItem(customView: button.left)
        let rightBarButtonItem = UIBarButtonItem(customView: button.right)
        self.navigationItem.leftBarButtonItem = leftBarButtonItem
        self.navigationItem.rightBarButtonItem = rightBarButtonItem
        self.navigationItem.title = "PR"
    }
}


extension ListingViewController: UISearchResultsUpdating {
    func updateSearchResults(for searchController: UISearchController) {
        dump(searchController.searchBar.text)
    }
}

extension ListingViewController: UISearchBarDelegate {
    func searchBarShouldBeginEditing(_ searchBar: UISearchBar) -> Bool {
        return true
    }
}
