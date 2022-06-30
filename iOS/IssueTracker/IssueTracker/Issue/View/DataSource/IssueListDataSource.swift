//
//  IssueListDataSource.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/15.
//

import UIKit

class IssueListDataSource: NSObject, UITableViewDataSource {
    private var data: Observable<[IssueItem]>

    init(issueEntityList: Observable<[IssueItem]>) {
        self.data = issueEntityList
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.value.count
    }

    func tableView(_ tableView: UITableView,
                   cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(
            withIdentifier: IssueListCell.identifier,
            for: indexPath) as? IssueListCell else {
            return UITableViewCell()
        }

        let issueEntity = data.value[indexPath.row]

        cell.configure(with: issueEntity)

        return cell
    }
}
