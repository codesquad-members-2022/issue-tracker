//
//  IssueListCell.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/24.
//

import SnapKit
import UIKit

final class IssueListCell: UITableViewCell {
    static let identifier = "IssueListCell"

    private let stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        stackView.spacing = 13
        stackView.alignment = .leading
        stackView.distribution = .equalSpacing
        return stackView
    }()

    private let milestoneStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 5
        stackView.alignment = .center
        stackView.distribution = .equalSpacing
        return stackView
    }()

    private let titleLabel: UILabel = {
        let label = UILabel()
        label.font = .systemFont(ofSize: 22, weight: .bold)
        return label
    }()

    private let descriptionLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 2
        label.font = .systemFont(ofSize: 17, weight: .regular)
        label.textColor = .darkGray
        return label
    }()

    private let icon: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: "signpost.right"), for: .normal)
        button.tintColor = .darkGray
        return button
    }()

    private let milestoneTitleLabel: UILabel = {
        let label = UILabel()
        label.font = .systemFont(ofSize: 15, weight: .regular)
        label.textColor = .gray
        return label
    }()

    private let badgeLabel: BadgeLabel = {
        let badgeLabel = BadgeLabel(frame: .zero)
        badgeLabel.padding = UIEdgeInsets(top: 10, left: 15, bottom: 10, right: 15)
        badgeLabel.font = .systemFont(ofSize: 12, weight: .bold)
        badgeLabel.textColor = .white
        badgeLabel.clipsToBounds = true
        badgeLabel.layer.cornerRadius = 17
        return badgeLabel
    }()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        layout()
    }

    @available(*, unavailable)
    required init?(coder _: NSCoder) {
        fatalError("Init with coder is unavailable")
    }

    private func layout() {
        addSubview(stackView)
        stackView.addArrangedSubview(titleLabel)
        stackView.addArrangedSubview(descriptionLabel)
        stackView.addArrangedSubview(milestoneStackView)
        stackView.addArrangedSubview(badgeLabel)

        milestoneStackView.addArrangedSubview(icon)
        milestoneStackView.addArrangedSubview(milestoneTitleLabel)

        stackView.snp.makeConstraints {
            $0.top.leading.equalToSuperview().offset(25)
            $0.bottom.trailing.equalToSuperview().offset(-25)
        }
    }
}

extension IssueListCell {
    func setInfo(title: String, description: String, milestoneTitle: String, badgeColor: UIColor, badgeTitle: String) {
        titleLabel.text = title
        descriptionLabel.text = description
        milestoneTitleLabel.text = milestoneTitle
        badgeLabel.backgroundColor = badgeColor
        badgeLabel.text = badgeTitle
    }
}
