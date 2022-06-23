//
//  LoginView.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/15.
//

import SnapKit
import UIKit

protocol LoginViewDelegate: AnyObject {
    func didClickGitHubLogin()
}

final class LoginView: UIView {
    weak var delegate: LoginViewDelegate?

    private let stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        stackView.spacing = 16
        return stackView
    }()

    private let titleLabel: UILabel = {
        let label = UILabel()
        label.text = "이슈트래커"
        label.font = .systemFont(ofSize: 45)
        label.textAlignment = .center
        return label
    }()

    private let githubLoginButton: LoginButton = {
        let button = LoginButton()
        button.setTitle("GitHub 계정으로 로그인", for: .normal)
        button.setImage(UIImage(named: "git"), for: .normal)
        return button
    }()

    private let appleLoginButton: LoginButton = {
        let button = LoginButton()
        button.setTitle("Apple 계정으로 로그인", for: .normal)
        button.setImage(UIImage(named: "apple"), for: .normal)
        return button
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .graybg
        layout()
        registerAction()
    }

    @available(*, unavailable)
    required init?(coder _: NSCoder) {
        fatalError("Init with coder is unavailable")
    }

    private func layout() {
        addSubview(stackView)
        stackView.addArrangedSubview(titleLabel)
        stackView.addArrangedSubview(githubLoginButton)
        stackView.addArrangedSubview(appleLoginButton)

        stackView.setCustomSpacing(40, after: titleLabel)

        githubLoginButton.snp.makeConstraints {
            $0.height.equalTo(60)
        }

        appleLoginButton.snp.makeConstraints {
            $0.height.equalTo(60)
        }

        stackView.snp.makeConstraints {
            $0.centerY.equalToSuperview()
            $0.leading.equalToSuperview().offset(50)
            $0.trailing.equalToSuperview().offset(-50)
        }
    }

    private func registerAction() {
        githubLoginButton.addAction(.init(handler: { [weak self] _ in
            self?.delegate?.didClickGitHubLogin()
        }), for: .touchUpInside)
    }
}
