//
//  IssueLabelDataSource.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/30.
//

import UIKit

final class IssueLabelDataSource: NSObject, UITableViewDataSource {
    
    private var issueLabels: [IssueLabel] = [
        IssueLabel(title: "테스트 Label", description: "테스트 테스트 테스트")
    ]
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        issueLabels.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: LabelTableViewCell.ID) as? LabelTableViewCell
        else { return UITableViewCell() }
        cell.setTitle(text: issueLabels[indexPath.row].title)
        cell.setDescription(text: issueLabels[indexPath.row].description)
        return cell
    }
}
