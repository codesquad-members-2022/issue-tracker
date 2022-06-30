//
//  PullListViewController.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/15.
//

import UIKit

class PullListViewController: UIViewController {
    
    @IBOutlet weak var loaderView: UIActivityIndicatorView!
    @IBOutlet weak var tableView: UITableView!
    
    private let viewModel = IssueListViewModel()
    private var isSearchControllerConfigured = false
    private let refreshControl = UIRefreshControl()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        configureBind()
        configureNavigationBar()
        configureRefreshControl()
        requestData()
    }
    
    private func configureBind() {
        viewModel.issueViewModels.bind { _ in
            DispatchQueue.main.async {
                UIView.transition(with: self.tableView,
                                  duration: 0.35,
                                  options: .transitionCurlDown,
                                  animations: {
                    self.tableView.reloadData()
                })
                self.loaderView.isHidden = true
            }
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
        navigationController?.navigationItem.hidesSearchBarWhenScrolling = true
        isSearchControllerConfigured = true
    }
    
    private func configureNavigationBar() {
        self.navigationItem.hidesSearchBarWhenScrolling = true
        self.navigationItem.title = "PR"
    }
    
    private func configureRefreshControl() {
        refreshControl.addTarget(self, action: #selector(didPullToRefresh), for: .valueChanged)
        tableView.refreshControl = refreshControl
    }
    
    @objc func didPullToRefresh() {
        DispatchQueue.main.async {
            self.requestData()
            self.tableView.reloadData()
            self.refreshControl.endRefreshing()
        }
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        if !self.isSearchControllerConfigured {
            DispatchQueue.main.async {
                self.configureSearchController()
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
            viewModel.close(at: indexPath.row) { isClosed in
                if !isClosed {
                    DispatchQueue.main.async {
                        self.alert(title: "알림", message: "닫기 실패", okTitle: "확인")
                    }
                }
            }
        }
    }
}


extension PullListViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, editingStyleForRowAt indexPath: IndexPath) -> UITableViewCell.EditingStyle {
        return .delete
    }
}
