//
//  PullListViewController.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/15.
//

import UIKit

class PullListViewController: UIViewController {
    
    @IBOutlet weak var tableView: UITableView!
    
    private let viewModel = PullListViewModel()
    private var isSearchControllerConfigured = false
    private var refreshControl = UIRefreshControl()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        configureBind()
        configureNavigationBar()
        configureRefreshControl()
        requestData()
    }
    
    private func configureBind() {
        viewModel.pullViewModelList.bind { _ in
            self.tableView.reloadData()
        }
    }
    
    private func requestData() {
        viewModel.requestPullListData()
    }
    
    private func configureSearchController() {
        let searchController = UISearchController(searchResultsController: nil)
        searchController.searchBar.placeholder = "Search"
        searchController.searchResultsUpdater = self
        searchController.searchBar.delegate = self
        searchController.searchBar.showsCancelButton = false
        searchController.hidesNavigationBarDuringPresentation = false
        
        self.navigationItem.searchController = searchController
        
        isSearchControllerConfigured = true
    }
    
    private func configureNavigationBar() {
        let button = CustomBarButton()
        let leftBarButtonItem = UIBarButtonItem(customView: button.left)
        let rightBarButtonItem = UIBarButtonItem(customView: button.right)
        self.navigationItem.leftBarButtonItem = leftBarButtonItem
        self.navigationItem.rightBarButtonItem = rightBarButtonItem
        self.navigationItem.title = "PR"
    }
    
    private func configureRefreshControl() {
        refreshControl.addTarget(self, action: #selector(didPullToRefresh), for: UIControl.Event.valueChanged)
        self.tableView.refreshControl = refreshControl
    }
    
    @objc func didPullToRefresh() {
        DispatchQueue.main.async {
            self.tableView.reloadData()
            self.refreshControl.endRefreshing()
            
            if !self.isSearchControllerConfigured {
                DispatchQueue.main.async {
                    self.configureSearchController()
                }
            }
        }
    }
}


extension PullListViewController: UISearchResultsUpdating {
    func updateSearchResults(for searchController: UISearchController) {
    }
}

extension PullListViewController: UISearchBarDelegate {
    func searchBarShouldBeginEditing(_ searchBar: UISearchBar) -> Bool {
        return true
    }
}

extension PullListViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.numberOfViewModels
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "cell",
                                                       for: indexPath) as? PullListTableViewCell,
              let cellViewModel = viewModel.getCellViewModel(index: indexPath.row) else {
            return UITableViewCell()
        }
        cell.configure(with: cellViewModel)
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 0
    }
    
    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }
}


extension PullListViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCell.EditingStyle {
        return .delete
    }
}
