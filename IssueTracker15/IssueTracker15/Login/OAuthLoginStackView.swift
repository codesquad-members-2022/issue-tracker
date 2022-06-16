//
//  OAuthLoginStackView.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/16.
//

import UIKit

final class OAuthLoginStackView: UIStackView {
    
    private let appleLoginButton: OAuthLoginButton = {
        let button = OAuthLoginButton()
        button.setServiceName(title: "Apple 계정으로 로그인")
        button.setServiceImage(image: .init(systemName: "applelogo"))
        return button
    }()
    
    private let githubLoginButton: OAuthLoginButton = {
        let button = OAuthLoginButton()
        button.setServiceName(title: "GitHub 계정으로 로그인")
        button.setServiceImage(image: .init(named: "githublogo"))
        return button
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addViews()
        setUp()
    }
    
    @available(*, unavailable) required init(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func addViews() {
        [githubLoginButton, appleLoginButton].forEach {
            self.addArrangedSubview($0)
        }
    }
    
    private func setUp() {
        self.axis = .vertical
        self.spacing = 16.0
        self.distribution = .fillEqually
    }
}
