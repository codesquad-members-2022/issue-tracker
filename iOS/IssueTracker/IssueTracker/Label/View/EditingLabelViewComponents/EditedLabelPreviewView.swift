//
//  EditedLabelPreviewView.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/29.
//

import UIKit

final class EditedLabelPreviewView: UIView {
    let labelPreviewView: PaddingLabel = {
        let label = PaddingLabel()
        label.setEdgeInset(top: 4, bottom: 4, left: 16, right: 16)
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        label.text = "레이블"
        label.backgroundColor = .orange
        label.clipsToBounds = true
        return label
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        setConfiguration()
        setSubviewsLayout()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        labelPreviewView.layer.cornerRadius = labelPreviewView.frame.height/2
    }
}

private extension EditedLabelPreviewView {
    func setConfiguration() {
        let screenSize = UIScreen.main.bounds.size

        snp.makeConstraints { make in
            make.height.equalTo(160/812 * screenSize.height)
        }
    }

    func setSubviewsLayout() {
        let screenSize = UIScreen.main.bounds.size

        let contentView = UIView()
        contentView.translatesAutoresizingMaskIntoConstraints = false
        contentView.backgroundColor = .gray5
        contentView.layer.cornerRadius = 20
        contentView.clipsToBounds = true

        addSubview(contentView)
        contentView.snp.makeConstraints { make in
            make.top.bottom.equalToSuperview()
            make.leading.equalToSuperview().offset(16/375 * screenSize.width)
            make.trailing.equalToSuperview().inset(16/375 * screenSize.width)
        }

        contentView.addSubview(labelPreviewView)
        labelPreviewView.snp.makeConstraints { make in
            make.centerX.centerY.equalToSuperview()
        }
        
    }
}
