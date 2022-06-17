//
//  LoginViewController.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/16.
//

import UIKit
import SnapKit

final class LoginViewController: UIViewController, ViewBinding {
    
    private var vm: LoginViewModel?
    private let userInfoInputStackView = UserInfoInputStackView()
    private let oAuthLoginStackView = OAuthLoginStackView()
    
    private var titleLabel: UILabel = {
        let label = UILabel()
        label.text = "Issue Tracker"
        label.font = .italicSystemFont(ofSize: 48.0)
        return label
    }()
    
    private lazy var loginButton: TestButton = {
        let button = TestButton()
        button.setTitle("로그인", for: .normal)
        button.setTitleColor(UIColor.systemBlue, for: .normal)
        button.setVC(self)
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
        vm = LoginViewModel { data, target in
            if let data = data {
                target.receive(data)
            }
        }
        
        addViews()
        setUp()
    }
    
    func inputViewEvent(_ target: ViewBindable, _ param: Any?) {
        self.vm?.request(target, param: param)
    }
    
    private func addViews() {
        [titleLabel, userInfoInputStackView, loginButton, makeIDButton, oAuthLoginStackView].forEach {
            self.view.addSubview($0)
        }
    }
    
    private func setUp() {
        titleLabel.snp.makeConstraints {
            let insetValue = UIScreen.main.bounds.height / 4
            $0.top.equalToSuperview().inset(insetValue)
            $0.centerX.equalToSuperview()
        }
        
        userInfoInputStackView.snp.makeConstraints {
            $0.top.equalTo(titleLabel.snp.bottom).offset(72.0)
            $0.leading.trailing.equalToSuperview()
        }
        
        loginButton.snp.makeConstraints {
            $0.top.equalTo(userInfoInputStackView.snp.bottom).offset(32.0)
            $0.leading.equalToSuperview().inset(96.0)
        }
        
        makeIDButton.snp.makeConstraints {
            $0.top.equalTo(userInfoInputStackView.snp.bottom).offset(32.0)
            $0.leading.equalTo(loginButton.snp.trailing).offset(79.0)
        }
        
        oAuthLoginStackView.snp.makeConstraints {
            $0.bottom.equalToSuperview().inset(60.0)
            $0.top.equalTo(userInfoInputStackView.snp.bottom).offset(228.0)
            $0.leading.trailing.equalToSuperview().inset(16.0)
        }
        
    }
}
