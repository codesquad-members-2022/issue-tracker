//
//  PullListTableViewController.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/16.
//

import UIKit

class PullListTableViewController: UITableViewController {
    
    private let viewModel = PullTableCellViewModel()
    private var viewModelLists = [PullTableCellViewModel]()
    private var cellData: PullListModel?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        refreshControl?.addTarget(self, action: #selector(didPullToRefresh), for: UIControl.Event.valueChanged)
        
        configureObserver()
    }
    
    private func configureObserver() {
        NotificationCenter.default.addObserver(forName: .PullListDidChange,
                                                           object: nil,
                                                           queue: .main) { noti in
            guard let datas = noti.userInfo?["cellViewModels"] as? [PullTableCellViewModel] else { return }
            self.viewModelLists = datas
        }
    }
    
    @objc func didPullToRefresh() {
        DispatchQueue.main.async {
            self.refreshControl?.endRefreshing()
            NotificationCenter.default.post(name: .TableViewDidReload, object: nil)
            self.tableView.reloadData()
        }
    }

    // MARK: - Table view data source
    override func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 0
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as? PullListTableViewCell else { return UITableViewCell() }
        cell.configure(with: viewModelLists[indexPath.row])
        return cell
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModelLists.count
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }
}
