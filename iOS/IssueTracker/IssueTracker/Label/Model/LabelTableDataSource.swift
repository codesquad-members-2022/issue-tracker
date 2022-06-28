//
//  LabelTableDataSource.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import UIKit

final class LabelTableDataSource: NSObject, UITableViewDataSource {

    // 임시로 가지고 있는 데이터입니다.
    let data = [
        LabelItem(title: "레이블 이름", description: "레이블에 대한 설명", backgroundColor: "#87878D"),
        LabelItem(title: "레이블 이름", description: "레이블에 대한 설명", backgroundColor: "#87878D"),
        LabelItem(title: "레이블 이름", description: "레이블에 대한 설명", backgroundColor: "#87878D")
    ]

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        data.count
    }
    
    func tableView(_ tableView: UITableView,
                   cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(
            withIdentifier: LabelTableCell.identifier,
            for: indexPath) as? LabelTableCell else {
            return UITableViewCell()
        }

        let labelEntity = data[indexPath.item]

        cell.configure(with: labelEntity)

        return cell
    }
}

struct LabelItem {
    let title: String
    let description: String
    let backgroundColor: String
}
