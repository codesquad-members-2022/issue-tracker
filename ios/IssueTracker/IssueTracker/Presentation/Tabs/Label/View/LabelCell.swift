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

    private let titleLabel: Label = {
        let title = Label()
        title.setPadding(top: 4, left: 16, bottom: 4, right: 16)
        title.text = "Label name"
        title.textColor = .white
        title.backgroundColor = .labelGray
        title.layer.cornerRadius = title.intrinsicContentSize.height / 2
        title.clipsToBounds = true
        return title
    }()

    private let descriptionLabel: Label = {
        let description = Label()
        description.text = "Description"
        description.textColor = .gray1
        return description
    }()

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
        let stack = UIStackView(arrangedSubviews: [titleLabel, descriptionLabel])
        stack.spacing = 16
        stack.axis = .vertical
        stack.alignment = .leading
        stack.distribution = .fillProportionally

        contentView.addSubview(stack)

        stack.snp.makeConstraints { make in
            make.horizontalEdges.equalToSuperview().inset(16)
            make.verticalEdges.equalToSuperview().inset(24)
        }
    }

    func setTitle(_ text: String, color: UIColor? = nil) {
        titleLabel.text = text
        titleLabel.backgroundColor = color ?? titleLabel.backgroundColor
    }

    func setDescription(_ text: String) {
        descriptionLabel.text = text
    }
}
