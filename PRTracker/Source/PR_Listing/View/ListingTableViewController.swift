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
        // TODO: TableViewCell에 들어갈 데이터 바인딩
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

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "cell", for: indexPath) as? ListingTableViewCell else { return UITableViewCell() }
        cell.title.text = cellData?.title
        cell.content.text = cellData?.content
        cell.projectName.text = cellData?.projectName
        return cell
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            tableView.deleteRows(at: [indexPath], with: .fade)
        }
    }
}
