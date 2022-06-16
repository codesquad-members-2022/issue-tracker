//
//  ListingTableViewController.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/16.
//

import UIKit

class ListingTableViewController: UITableViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        refreshControl?.addTarget(self, action: #selector(didPullToRefresh), for: UIControl.Event.valueChanged)
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

    /*
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseIdentifier", for: indexPath)

        // Configure the cell...

        return cell
    }
    */
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }
}
