//
//  LabelTableViewCell.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/29.
//

import UIKit
import SnapKit

final class LabelTableViewCell: UITableViewCell {
    static let ID: String = "LabelTableViewCell"
    
    private lazy var issueTitle: IssueTitleButton = IssueTitleButton()
    
    private lazy var descriptionLabel: UILabel = {
       let label = UILabel()
        label.lineBreakMode = .byTruncatingTail
        return label
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        addViews()
        setUp()
    }
    
    @available (*, unavailable) required init?(coder: NSCoder) {
        fatalError("init with coder is unavailable")
    }
    
    func setTitle(text: String) {
        self.issueTitle.configuration?.title = text
    }
    
    func setTitleBackgroundColor(color: UIColor) {
        self.issueTitle.configuration?.baseBackgroundColor = color
    }
    
    func setDescription(text: String) {
        self.descriptionLabel.text = text
    }
    
    private func addViews() {
        [issueTitle, descriptionLabel].forEach {
            self.addSubview($0)
        }
    }
    
    private func setUp() {
        issueTitle.snp.makeConstraints {
            $0.leading.equalToSuperview().inset(16.0)
            $0.top.equalToSuperview().inset(16.0)
        }
        
        descriptionLabel.snp.makeConstraints {
            $0.leading.equalTo(issueTitle.snp.leading)
            $0.top.equalTo(issueTitle.snp.bottom)
            $0.bottom.equalToSuperview()
        }
    }
}
