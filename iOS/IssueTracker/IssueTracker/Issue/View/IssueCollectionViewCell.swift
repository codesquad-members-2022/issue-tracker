//
//  IssueCollectionViewCell.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/14.
//

import SnapKit

final class IssueCollectionViewCell: UICollectionViewCell {
    
    static let identifier = "IssueCollectionViewCell"
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubviews(title, issueDescription, milestoneImage, milestoneName, labelStack)
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private var title: UILabel = {
        let title = UILabel()
        title.font = UIFont(name: "SFProDisplay-Bold", size: 22)
        title.translatesAutoresizingMaskIntoConstraints = false
        return title
    }()
    
    private var issueDescription: UILabel = {
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
    
    func configure(with items: IssueItem) {
        self.title.text = items.title
        self.issueDescription.text = items.content
        self.milestoneName.text = items.milestoneName

        items.labels.forEach { label in
            let newLabel = LabelFactory.createLael(label)
            labelStack.addArrangedSubview(newLabel)
            newLabel.layer.cornerRadius = 15
        }
    }
    
    override func layoutSubviews() {
        
        title.snp.makeConstraints { make in
            make.top.equalTo(self.contentView).offset(24)
            make.leading.equalTo(self.contentView).offset(16)
            make.trailing.lessThanOrEqualTo(-16)
        }
        
        issueDescription.snp.makeConstraints { make in
            make.top.equalTo(title.snp.bottom).offset(16)
            make.leading.equalTo(title.snp.leading)
            make.trailing.equalTo(self.contentView).offset(-16)
        }
        
        milestoneImage.snp.makeConstraints { make in
            make.top.equalTo(issueDescription.snp.bottom).offset(18)
            make.leading.equalTo(title.snp.leading)
        }
        
        milestoneName.snp.makeConstraints { make in
            make.top.equalTo(issueDescription.snp.bottom).offset(18)
            make.leading.equalTo(milestoneImage.snp.trailing).offset(4)
            make.trailing.lessThanOrEqualTo(-16)
        }
        
        labelStack.snp.makeConstraints { make in
            make.top.equalTo(milestoneImage.snp.bottom).offset(16)
            make.leading.equalTo(title)
            make.bottom.lessThanOrEqualTo(-24)
        }
    }
}
