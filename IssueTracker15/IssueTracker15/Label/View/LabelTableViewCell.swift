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
    
    private lazy var IssueLabel: IssueLabel = {
        let label = IssueLabel
        return label
    }()
    
    private lazy var descriptionLabel: UILabel = {
       let label = UILabel()
        label.text = "설명설명설명설명설명설명설명설명설명설명"
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
    
    private func addViews() {
        [IssueLabel, descriptionLabel].forEach {
            self.addSubview($0)
        }
    }
    
    private func setUp() {
        IssueLabel.snp.makeConstraints {
            $0.leading.equalToSuperview().inset(16.0)
            $0.top.equalToSuperview().inset(16.0)
        }
        
        descriptionLabel.snp.makeConstraints {
            $0.leading.equalTo(IssueLabel.snp.leading)
            $0.top.equalTo(IssueLabel.snp.bottom)
        }
    }
}
