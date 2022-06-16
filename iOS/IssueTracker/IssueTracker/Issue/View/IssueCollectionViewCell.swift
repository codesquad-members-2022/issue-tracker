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
        addSubviews(title, issueDescription, milestoneImage, milestoneName, labelName)
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private var title: UILabel = {
        let title = UILabel()
        let titleAttributedString = NSMutableAttributedString(string: "제목")
        title.font = UIFont(name: "SFProDisplay-Bold", size: 22)
        title.attributedText = titleAttributedString
        title.translatesAutoresizingMaskIntoConstraints = false
        return title
    }()
    
    private var issueDescription: UILabel = {
        let issueDescription = UILabel()
        let issueDescriptionAttributedString = NSMutableAttributedString(string: "마일스톤 이름")
        issueDescription.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        issueDescription.attributedText = issueDescriptionAttributedString
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
        let milestoneNameAttributedString = NSMutableAttributedString(string: "마일스톤 이름")
        milestoneName.attributedText = milestoneNameAttributedString
        milestoneName.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        milestoneName.translatesAutoresizingMaskIntoConstraints = false
        return milestoneName
    }()
    
    private let labelName: UILabel = {
        let labelName = UILabel()
        let labelNameAttributedString = NSMutableAttributedString(string: "  레이블이름  ")
        labelName.attributedText = labelNameAttributedString
        labelName.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        labelName.clipsToBounds = true
        labelName.layer.cornerRadius = 10
        labelName.backgroundColor = .systemGray
        labelName.translatesAutoresizingMaskIntoConstraints = false
        return labelName
    }()
    
    func configure(title: String?, issueDescription: String?, milestoneName: String?) {
        self.title.text = title
        self.issueDescription.text = issueDescription
        self.milestoneName.text = milestoneName
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
        
        labelName.snp.makeConstraints { make in
            make.top.equalTo(milestoneImage.snp.bottom).offset(16)
            make.leading.equalTo(title)
            make.bottom.lessThanOrEqualTo(-24)
        }
    }
    
}
