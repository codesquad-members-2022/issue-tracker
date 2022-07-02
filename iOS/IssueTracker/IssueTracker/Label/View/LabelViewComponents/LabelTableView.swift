//
//  LabelTableView.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/27.
//

import UIKit

final class LabelTableView: UITableView {
    override init(frame: CGRect, style: UITableView.Style) {
        super.init(frame: frame, style: style)
        backgroundColor = .white
        translatesAutoresizingMaskIntoConstraints = false
        register(LabelTableCell.self, forCellReuseIdentifier: LabelTableCell.identifier)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}
