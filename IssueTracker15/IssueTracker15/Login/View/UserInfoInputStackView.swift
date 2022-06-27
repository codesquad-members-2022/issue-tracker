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
    
    private let validatedResultLabel: UILabel = {
        let label = UILabel()
        label.textAlignment = .center
        label.numberOfLines = 2
        label.font = .systemFont(ofSize: 12.0, weight: .light)
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUp()
        addViews()
    }
    
    @available(*, unavailable) required init(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setVC(_ binding: ViewBinding) {
        [IDTextField, PasswordTextField].forEach {
            $0.setVC(binding)
        }
    }
    
    func setValidatedResultLabel(_ validateType: ValidatedResult) {
        switch validateType {
        case .success:
            self.validatedResultLabel.text = ""
        case .unValidatedID:
            self.validatedResultLabel.text = "에러: 유효하지 않은 아이디형식\n@ 이전까지, (영)소,대문자 혹은 숫자로 6~16자의 아이디를 작성해주세요."
            self.validatedResultLabel.textColor = .red
        case .unValidatePassword:
            self.validatedResultLabel.text = "에러: 유효하지 않은 비밀번호형식\n(영)대문자,소문자,특수문자,숫자를 최소 하나씩 6~12자의 비밀번호를 작성해주세요."
            self.validatedResultLabel.textColor = .red
        }
    }
    
    private func addViews() {
        [IDStackView, separatorLineView, passwordStackView, validatedResultLabel].forEach {
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
