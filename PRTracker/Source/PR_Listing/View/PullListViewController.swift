//
//  PullListViewController.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/15.
//

import UIKit

class PullListViewController: UIViewController {

    private let viewModel = PullListViewModel()
    private var modelData: [PullListModel]?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        bind()
        configureObserver()
        configureNavigationBar()
        requestData()
    }
    
    private func bind() {
        viewModel.mockData.bind { modelLists in
            self.modelData = modelLists
        }
    }
    
    private func requestData() {
        viewModel.requestData()
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
    
    private func configureObserver() {
        NotificationCenter.default.addObserver(forName: .TableViewDidReload,
                                                           object: nil,
                                                           queue: .main) { _ in
            DispatchQueue.main.async {
                self.configureSearchController()
            }
        }
    }
}


extension PullListViewController: UISearchResultsUpdating {
    func updateSearchResults(for searchController: UISearchController) {
        dump(searchController.searchBar.text)
    }
}

extension PullListViewController: UISearchBarDelegate {
    func searchBarShouldBeginEditing(_ searchBar: UISearchBar) -> Bool {
        return true
    }
}
