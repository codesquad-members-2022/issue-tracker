//
//  LabelTableCell.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/27.
//

import SnapKit

final class LabelTableCell: UITableViewCell {
    static let identifier = String(describing: LabelTableCell.self)

    private let labelLabel: PaddingLabel = {
        let label = PaddingLabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.setEdgeInset(top: 4, bottom: 4, left: 16, right: 16)
        label.textColor = .white
        label.numberOfLines = 1
        label.clipsToBounds = true
        label.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        return label
    }()

    private let descriptionLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.textColor = .gray
        label.numberOfLines = 1
        label.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        return label
    }()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        contentView.addSubviews(labelLabel, descriptionLabel)
        setSubviewsLayout()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func configure(with label: LabelItem) {
        labelLabel.text = label.title
        descriptionLabel.text = label.description
        labelLabel.backgroundColor = UIColor(hex: label.backgroundColor)
    }

    override func layoutSubviews() {
        super.layoutSubviews()

        labelLabel.layer.cornerRadius = labelLabel.frame.height/2
    }
}

private extension LabelTableCell {
    func setSubviewsLayout() {
        let screenSize = UIScreen.main.bounds

        labelLabel.snp.makeConstraints { make in
            make.top.equalTo(contentView).offset(24/812 * screenSize.height)
            make.leading.equalTo(contentView).offset(16/375 * screenSize.width)
            make.height.equalTo(30/812 * screenSize.height)
        }

        descriptionLabel.snp.makeConstraints { make in
            make.top.equalTo(labelLabel.snp.bottom).offset(16/812 * screenSize.height)
            make.leading.equalTo(labelLabel)
            make.bottom.equalTo(contentView.snp.bottom).inset(25/812 * screenSize.height)
        }
    }
}
