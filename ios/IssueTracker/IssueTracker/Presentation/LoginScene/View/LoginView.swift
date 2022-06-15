//
//  LoginView.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/15.
//

import SnapKit
import UIKit

final class LoginView: UIView {
    private let stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        stackView.spacing = 16
        return stackView
    }()

    private let titleLabel: UILabel = {
        let label = UILabel()
        label.text = "Issue Tracker"
        label.font = .systemFont(ofSize: 45)
        label.textAlignment = .center
        return label
    }()

    private let githubLoginButton: UIButton = {
        var config = UIButton.Configuration.bordered()
        config.baseBackgroundColor = .white
        config.baseForegroundColor = .black
        config.background.strokeColor = .line
        config.cornerStyle = .medium
        config.title = "GitHub 계정으로 로그인"
        config.image = UIImage(named: "git")
        config.imagePadding = 10
        config.imagePlacement = .leading

        let button = UIButton(configuration: config)
        return button
    }()

    private let appleLoginButton: UIButton = {
        var config = UIButton.Configuration.bordered()
        config.baseBackgroundColor = .white
        config.baseForegroundColor = .black
        config.background.strokeColor = .line
        config.cornerStyle = .medium
        config.title = "Apple 계정으로 로그인"
        config.image = UIImage(named: "apple")
        config.imagePadding = 10
        config.imagePlacement = .leading

        let button = UIButton(configuration: config)
        return button
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .graybg
        layout()
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
}
