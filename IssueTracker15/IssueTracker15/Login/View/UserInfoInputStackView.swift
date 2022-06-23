//
//  UserInfoInputStackView.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/16.
//

import UIKit

enum UserInputType {
    case ID
    case password
}

final class UserInfoInputStackView: UIStackView {
    
    private let IDLabel: UILabel = {
        let label = UILabel()
        label.text = "아이디"
        return label
    }()
    
    private let IDTextField: UserInfoTextField = {
        let textField = UserInfoTextField()
        textField.setPlaceHolder("아이디를 입력해주세요")
        textField.setType(.ID)
        return textField
    }()
    
    private lazy var IDStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 24
        stackView.distribution = .fillProportionally
        stackView.addArrangedSubview(IDLabel)
        stackView.addArrangedSubview(IDTextField)
        return stackView
    }()
    
    private let passwordLabel: UILabel = {
        let label = UILabel()
        label.text = "비밀번호"
        return label
    }()
    
    private let PasswordTextField: UserInfoTextField = {
        let textField = UserInfoTextField()
        textField.setPlaceHolder("비밀번호를 입력해주세요")
        textField.setType(.password)
        return textField
    }()
    
    private lazy var passwordStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 8
        stackView.distribution = .fillProportionally
        stackView.addArrangedSubview(passwordLabel)
        stackView.addArrangedSubview(PasswordTextField)
        return stackView
    }()

    private let separatorLineView = UIView.makeSeparator()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUp()
        addViews()
    }
    
    @available(*, unavailable) required init(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func addViews() {
        [IDStackView, separatorLineView, passwordStackView].forEach {
            self.addArrangedSubview($0)
        }
    }
    
    private func setUp() {
        self.backgroundColor = .white
        self.axis = .vertical
        self.spacing = 8
        self.isLayoutMarginsRelativeArrangement = true
        self.directionalLayoutMargins =  NSDirectionalEdgeInsets(top: 10, leading: 16, bottom: 10, trailing: 16)
    }
}
