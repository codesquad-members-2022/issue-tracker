//
//  SignInView.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/15.
//

import SnapKit
import UIKit

final class SignInView: UIView {
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "Issue Tracker"
        label.font = UIFont(name: "Montserrat", size: 48)
        label.minimumScaleFactor = 0.5
        label.adjustsFontSizeToFitWidth = true
        return label
    }()

    private let signInWithGitHubButton: UIButton = {
        let button = UIButton()
        var configuration = UIButton.Configuration.filled()
        button.translatesAutoresizingMaskIntoConstraints = false
        configuration.image = UIImage(named: "github-icon")
        configuration.baseBackgroundColor = .black
        configuration.imagePadding = 10
        configuration.cornerStyle = .medium
        var attributedTitle = AttributedString.init("GitHub 계정으로 로그인하기")
        attributedTitle.font = UIFont(name: "SFProDisplay-Bold", size: 17)
        configuration.attributedTitle = attributedTitle
        button.configuration = configuration
        return button
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        addSubviews(titleLabel, signInWithGitHubButton)
        backgroundColor = .white
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError()
    }

    override func layoutSubviews() {
        let screenSize = UIScreen.main.bounds

        titleLabel.snp.makeConstraints { make in
            make.top.equalTo(self).offset(165/812 * screenSize.height)
            make.centerX.equalTo(self)
            make.leading.equalTo(self).offset(40/375 * screenSize.width)
            make.height.equalTo(72)
        }

        signInWithGitHubButton.snp.makeConstraints { make in
            make.centerX.equalTo(self)
            make.bottom.equalTo(self).inset(130/812 * screenSize.height)
            make.leading.equalTo(self).offset(18/375 * screenSize.width)
            make.height.equalTo(56)
        }
    }
}
