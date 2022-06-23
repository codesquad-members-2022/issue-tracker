//
//  NewIssueOptionCell.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/23.
//

import UIKit

class NewIssueOptionCell: UITableViewCell {
    
    private lazy var title: UILabel = {
        var label = UILabel()
        return label
    }()
    
    private lazy var selectedOption: UILabel = {
        var label = UILabel()
        label.textColor = .systemGray
        return label
    }()
    
    private lazy var rightButton: UIButton = {
        var button = UIButton()
        button.setImage(UIImage(systemName: "chevron.right"), for: .normal)
        button.tintColor = .systemGray
        return button
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: "NewIssueOptionCell")
        setupViews()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    func setTitle(text: String) {
        title.text = text
    }
    
    func setSelectedOption(text: String) {
        selectedOption.text = text
    }
    
    private func setupViews() {
        self.addSubview(title)
        title.snp.makeConstraints { make in
            make.top.leading.equalTo(self).offset(5)
            make.bottom.equalTo(self).offset(-5)
        }
        
        self.addSubview(rightButton)
        rightButton.snp.makeConstraints { make in
            make.top.equalTo(self).offset(5)
            make.trailing.bottom.equalTo(self).offset(-5)
        }
        
        self.addSubview(selectedOption)
        selectedOption.snp.makeConstraints { make in
            make.top.equalTo(self).offset(5)
            make.trailing.equalTo(rightButton.snp.leading).offset(-5)
            make.bottom.equalTo(self).offset(-5)
        }
    }
}
