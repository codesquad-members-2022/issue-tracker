//
//  LabelTableDataSource.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import UIKit

final class LabelTableDataSource: NSObject, UITableViewDataSource {

    var data: Observable<[LabelItem]> = Observable([])

    init(labelItemData: Observable<[LabelItem]>) {
        data = labelItemData
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        data.value.count
    }
    
    func tableView(_ tableView: UITableView,
                   cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(
            withIdentifier: LabelTableCell.identifier,
            for: indexPath) as? LabelTableCell else {
            return UITableViewCell()
        }

        let labelEntity = data.value[indexPath.item]

        cell.configure(with: labelEntity)

        return cell
    }
}
