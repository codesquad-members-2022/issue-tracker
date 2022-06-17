//
//  ListingTableViewController.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/16.
//

import UIKit

class ListingTableViewController: UITableViewController {
    
    private let viewModel = PRTableCellViewModel()
    private var cellData: ListingModel?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        bind()
        refreshControl?.addTarget(self, action: #selector(didPullToRefresh), for: UIControl.Event.valueChanged)
    }
    
    private func bind() {
    }
    
    @objc func didPullToRefresh() {
        DispatchQueue.main.async {
            self.refreshControl?.endRefreshing()
            NotificationCenter.default.post(name: .TableViewDidReload, object: nil)
        }
    }

    // MARK: - Table view data source
    override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 0
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }
}
