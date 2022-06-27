//
//  IssueListCollectionHeaderReusableView.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/21.
//

import UIKit

class IssueListCollectionHeaderReusableView: UICollectionReusableView {
    
    let titleLabel: UILabel = {
        let label = UILabel()
        label.textColor = .label
        label.font = label.font.withSize(34)
        label.text = "이슈 선택"
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        addSubview(titleLabel)
        titleLabel.snp.makeConstraints {
            $0.top.bottom.equalToSuperview().offset(16)
            $0.leading.trailing.equalToSuperview().inset(8)
        }
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
}
