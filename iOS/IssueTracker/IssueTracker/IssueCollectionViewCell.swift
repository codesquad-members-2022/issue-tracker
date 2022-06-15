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
        appendSubView()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private var title: UILabel = {
        let title = UILabel()
        let titleAttributedString = NSMutableAttributedString(string: "제목")
        let length = titleAttributedString.length
        titleAttributedString.addAttributes([.foregroundColor:UIColor.black,
                                        .font:UIFont.systemFont(ofSize: 22, weight: .bold)],
                                       range: NSRange(location: 0, length: length))
        title.attributedText = titleAttributedString
        title.translatesAutoresizingMaskIntoConstraints = false
        return title
    }()
    
    private var issueDescription: UILabel = {
        let issueDescription = UILabel()
        let issueDescriptionFont = UIFont(name: "Helvetica", size: 17)
        let issueDescriptionAttributedString = NSMutableAttributedString(string: "마일스톤 이름")
        issueDescription.attributedText = issueDescriptionAttributedString
        issueDescription.font = issueDescriptionFont
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
        let milestoneNameFont = UIFont(name: "Helvetica", size: 17)
        let milestoneNameAttributedString = NSMutableAttributedString(string: "마일스톤 이름")
        milestoneName.attributedText = milestoneNameAttributedString
        milestoneName.font = milestoneNameFont
        milestoneName.translatesAutoresizingMaskIntoConstraints = false
        return milestoneName
    }()
    
    private let labelName: UIButton = {
        let labelName = UIButton()
        labelName.setTitle("  레이블이름  ", for: .normal)
        labelName.setTitleColor(UIColor.white, for: .normal)
        labelName.backgroundColor = .lightGray
        labelName.titleLabel?.font = UIFont.systemFont(ofSize: 17, weight: .bold)
        labelName.layer.cornerRadius = 10
        labelName.translatesAutoresizingMaskIntoConstraints = false
        return labelName
    }()
    
    func configure(title: String?, issueDescription: String?, milestoneName: String?) {
        self.title.text = title
        self.issueDescription.text = issueDescription
        self.milestoneName.text = milestoneName
    }
    
    private func appendSubView() {
        contentView.addSubview(title)
        contentView.addSubview(issueDescription)
        contentView.addSubview(milestoneImage)
        contentView.addSubview(milestoneName)
        contentView.addSubview(labelName)
    }
    
    override func layoutSubviews() {
        
        title.snp.makeConstraints { make in
            make.top.equalTo(self.contentView).offset(24)
            make.leading.equalTo(self.contentView).offset(16)
        }
        
        issueDescription.snp.makeConstraints { make in
            make.top.equalTo(title).offset(16)
            make.leading.equalTo(title)
            make.trailing.equalTo(self.contentView).offset(16)
        }
        
        milestoneImage.snp.makeConstraints { make in
            make.top.equalTo(issueDescription).offset(18)
            make.leading.equalTo(title)
        }
        
        milestoneName.snp.makeConstraints { make in
            make.top.equalTo(issueDescription).offset(18)
            make.leading.equalTo(milestoneImage).offset(4)
        }
        
        labelName.snp.makeConstraints { make in
            make.top.equalTo(milestoneImage).offset(16)
            make.leading.equalTo(title)
        }
    }
    
}
