//
//  FilterViewController.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/29.
//

import UIKit

class FilterViewController: UIViewController {

    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}


extension FilterViewController: UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        switch section {
        case 0:
            return "상태"
        case 1:
            return "작성자"
        default:
            return "레이블"
        }
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return 3
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        switch indexPath.section {
        case 0:
            guard let cell = tableView.dequeueReusableCell(withIdentifier: "filterCell", for: indexPath) as?  FilterTableViewCell else { return UITableViewCell() }
            cell.configure(with: "상태 \(indexPath.row)")
            return cell
        case 1:
            guard let cell = tableView.dequeueReusableCell(withIdentifier: "filterCell", for: indexPath) as?  FilterTableViewCell else { return UITableViewCell() }
            cell.configure(with: "작성자 \(indexPath.row)")
            return cell
        default:
            guard let cell = tableView.dequeueReusableCell(withIdentifier: "filterCell", for: indexPath) as?  FilterTableViewCell else { return UITableViewCell() }
            cell.configure(with: "레이블 \(indexPath.row)")
            return cell
        }
    }
}
