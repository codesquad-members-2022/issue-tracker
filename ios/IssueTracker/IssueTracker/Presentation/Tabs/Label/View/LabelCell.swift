//
//  LabelCell.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/29.
//

import SnapKit
import UIKit

final class LabelCell: UITableViewCell {
    static let reuseIdentifier = "LabelCell"

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureUI()
    }

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        configureUI()
    }

    private func configureUI() {
        selectionStyle = .none
        configureContent()
    }

    private func configureContent() {
        let title = Label()
        title.setPadding(top: 4, left: 16, bottom: 4, right: 16)
        title.text = "Label name"
        title.backgroundColor = .gray

        let description = UILabel()
        description.text = "Description"

        let stack = UIStackView(arrangedSubviews: [title, description])
        stack.axis = .vertical
        stack.spacing = 16
        stack.distribution = .fillProportionally

        contentView.addSubview(stack)

        stack.snp.makeConstraints { make in
            make.horizontalEdges.equalToSuperview().inset(16)
            make.verticalEdges.equalToSuperview().inset(24)
        }
    }

    func setTitle() {}

    func setSubtitle() {}
}
