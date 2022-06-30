//
//  IssueListCell.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/14.
//

import UIKit
import SnapKit

final class IssueListCell: UITableViewCell {
    static let identifier = String(describing: IssueListCell.self)

    private var titleLabel: UILabel = {
        let title = UILabel()
        title.font = UIFont(name: "SFProDisplay-Bold", size: 22)
        title.translatesAutoresizingMaskIntoConstraints = false
        return title
    }()
    
    private var issueDescriptionLabel: UILabel = {
        let issueDescription = UILabel()
        issueDescription.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        issueDescription.translatesAutoresizingMaskIntoConstraints = false
        return issueDescription
    }()
    
    private let milestoneImage: UIImageView = {
        let milestoneImage = UIImageView(image: UIImage(named: "milestone"))
        milestoneImage.translatesAutoresizingMaskIntoConstraints = false
        return milestoneImage
    }()
    
    private var milestoneName: UILabel = {
        let milestoneName = UILabel()
        milestoneName.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        milestoneName.translatesAutoresizingMaskIntoConstraints = false
        return milestoneName
    }()

    private let labelStack: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .horizontal
        stackView.spacing = 8
        return stackView
    }()

    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        contentView.addSubviews(titleLabel, issueDescriptionLabel, milestoneImage, milestoneName, labelStack)
        setSubviewsLayout()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func configure(with viewModel: IssueItem) {
        self.titleLabel.text = viewModel.title
        self.issueDescriptionLabel.text = viewModel.content
        self.milestoneName.text = viewModel.milestoneName

        viewModel.labels.forEach { label in
            let newLabel = LabelFactory.createLabel(label)
            labelStack.addArrangedSubview(newLabel)
            newLabel.layer.cornerRadius = 15
        }
    }
}

private extension IssueListCell {
    func setSubviewsLayout() {
        let screenSize = UIScreen.main.bounds.size
        titleLabel.snp.makeConstraints { make in
            make.top.equalToSuperview().offset(24/812 * screenSize.height)
            make.leading.equalToSuperview().offset(16/375 * screenSize.width).priority(.high)
            make.trailing.equalToSuperview()
        }
        
        issueDescriptionLabel.snp.makeConstraints { make in
            make.top.equalTo(titleLabel.snp.bottom).offset(16/812 * screenSize.height)
            make.leading.equalTo(titleLabel)
            make.trailing.equalToSuperview()
        }
        
        milestoneImage.snp.makeConstraints { make in
            make.top.equalTo(issueDescriptionLabel.snp.bottom).offset(18/812 * screenSize.height)
            make.leading.equalTo(titleLabel)
            make.height.equalTo(17/812 * screenSize.height)
            make.width.equalTo(milestoneImage.snp.height)
        }
        
        milestoneName.snp.makeConstraints { make in
            make.top.equalTo(issueDescriptionLabel.snp.bottom).offset(18/812 * screenSize.height)
            make.leading.equalTo(milestoneImage.snp.trailing).offset(4/375 * screenSize.width)
            make.trailing.equalToSuperview()
        }
        
        labelStack.snp.makeConstraints { make in
            make.top.equalTo(milestoneImage.snp.bottom).offset(16/812 * screenSize.height)
            make.leading.equalTo(titleLabel)
            make.bottom.equalToSuperview().inset(24/375 * screenSize.width)
        }
    }
}
