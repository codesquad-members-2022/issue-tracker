//
//  LoginViewController.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/16.
//

import UIKit
import SnapKit

final class LoginViewController: UIViewController, ViewBinding {
    
    private var loginViewModel: CommonViewModel?
    private var userInputViewModel: CommonViewModel?
    
    private lazy var userInfoInputStackView: UserInfoInputStackView = {
        let stackView = UserInfoInputStackView()
        stackView.setVC(self)
        return stackView
    }()
    
    private lazy var oAuthLoginStackView: OAuthLoginStackView = {
        let stackView = OAuthLoginStackView()
        stackView.subviews.forEach {
            guard let bindableView = $0 as? ViewBindable else { return }
            bindableView.setVC(self)
        }
        return stackView
    }()
    
    private var titleLabel: UILabel = {
        let label = UILabel()
        label.text = "Issue Tracker"
        label.font = .italicSystemFont(ofSize: 48.0)
        return label
    }()
    
    private lazy var loginButton: UIButton = {
        let button = UIButton()
        button.setTitle("로그인", for: .normal)
        button.setTitleColor(UIColor.systemBlue, for: .normal)
        return button
    }()
    
    private let makeIDButton: UIButton = {
        let button = UIButton()
        button.setTitle("회원가입", for: .normal)
        button.setTitleColor(UIColor.systemBlue, for: .normal)
        return button
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        loginViewModel = LoginViewModel { loginUrl, _ in
            guard let loginUrl = loginUrl as? URL else { return }
            UIApplication.shared.open(loginUrl)
        }
        
        userInputViewModel = UserInputViewModel { result, _ in
            guard let result = result as? ValidatedResult else { return }
            self.userInfoInputStackView.setValidatedResultLabel(result)
        }
        
        addViews()
        setUp()
    }
    
    func inputViewEvent(_ target: ViewBindable, _ param: Any?) {
        switch target {
        case let target as UserInfoTextField:
            self.userInputViewModel?.request(target, param: param)
        case let target as OAuthLoginButton:
            self.loginViewModel?.request(target, param: param)
        default:
            break
        }
    }
    
    func presentIssueList(accessToken: String) {
        let issueListVC = MainTabBarController()
        self.navigationController?.pushViewController(issueListVC, animated: true)
    }
    
    private func addViews() {
        [titleLabel, userInfoInputStackView, loginButton, makeIDButton, oAuthLoginStackView].forEach {
            self.view.addSubview($0)
        }
    }
    
    private func setUp() {
        let screenHeight = UIScreen.main.bounds.height
        let screenWeight = UIScreen.main.bounds.width
        
        titleLabel.snp.makeConstraints {
            $0.top.equalToSuperview()
                .inset(screenHeight * LayoutRatio.titleTopInsetRatio)
            $0.centerX.equalToSuperview()
        }
        
        userInfoInputStackView.snp.makeConstraints {
            $0.top.equalToSuperview()
                .inset(screenHeight * LayoutRatio.userInfoInputStackViewInsetRatio)
            $0.leading.trailing.equalToSuperview()
        }

        loginButton.snp.makeConstraints {
            $0.top.equalToSuperview()
                .inset(screenHeight * LayoutRatio.loginButtonTopInsetRatio)
            $0.leading.equalToSuperview()
                .inset(screenWeight * LayoutRatio.loginButtonLeadingInsetRatio)
        }

        makeIDButton.snp.makeConstraints {
            $0.top.equalToSuperview()
                .inset(screenHeight * LayoutRatio.makeIdButtonTopInsetRatio)
            $0.trailing.equalToSuperview()
                .inset(screenWeight * LayoutRatio.makeIdButtonTrailingInsetRatio)
        }

        oAuthLoginStackView.snp.makeConstraints {
            $0.bottom.equalToSuperview()
                .inset(screenHeight * LayoutRatio.oAuthStackViewBottomInsetRatio)
            $0.trailing.leading.equalToSuperview()
                .inset(screenWeight * LayoutRatio.oAuthStackViewSideInsetRatio)
        }
    }
}

private struct LayoutRatio {
    static let titleTopInsetRatio: CGFloat = 165 / 812
    static let userInfoInputStackViewInsetRatio: CGFloat = 312 / 812
    
    static let loginButtonTopInsetRatio: CGFloat = 430 / 812
    static let loginButtonLeadingInsetRatio: CGFloat = 96 / 375
    
    static let makeIdButtonTopInsetRatio: CGFloat = 430 / 812
    static let makeIdButtonTrailingInsetRatio: CGFloat = 96 / 375
    
    static let oAuthStackViewBottomInsetRatio: CGFloat = 60 / 812
    static let oAuthStackViewSideInsetRatio: CGFloat = 16 / 375
}
