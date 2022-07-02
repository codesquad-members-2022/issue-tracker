//
//  TextFieldWithTitleView.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/29.
//

import UIKit
import SnapKit

final class TextFieldWithTitleView: UIView {
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        label.numberOfLines = 1
        return label
    }()

    let editableTextField: UITextField = {
        let textField = UITextField()
        textField.translatesAutoresizingMaskIntoConstraints = false
        textField.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        textField.autocorrectionType = .no
        textField.autocapitalizationType = .none
        return textField
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubviews(titleLabel, editableTextField)
        translatesAutoresizingMaskIntoConstraints = false
        clipsToBounds = true
        setSubviewsLayout()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setText(title: String, placeholder: String) {
        titleLabel.text = title
        editableTextField.placeholder = placeholder
    }
}

private extension TextFieldWithTitleView {
    func setSubviewsLayout() {
        let screenSize = UIScreen.main.bounds.size

        titleLabel.snp.makeConstraints { make in
            make.top.equalToSuperview()
            make.leading.equalToSuperview().offset(20/375 * screenSize.width)
            make.width.equalTo(32/375 * screenSize.width)
        }

        editableTextField.snp.makeConstraints { make in
            make.top.equalTo(titleLabel)
            make.leading.equalTo(titleLabel.snp.trailing).offset(72/375 * screenSize.width)
            make.trailing.equalToSuperview()
        }

        snp.makeConstraints { make in
            make.height.equalTo(22/812 * screenSize.height)
        }
    }
}
