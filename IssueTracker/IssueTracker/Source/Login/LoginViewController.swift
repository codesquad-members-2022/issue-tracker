//
//  LoginViewController.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/13.
//

import UIKit
import SnapKit

class LoginViewController: UIViewController {

    var viewModel: LoginViewModel

    private lazy var githubLoginButton: UIButton = {
        let button = UIButton()
        button.setTitle("gitbutton", for: .normal)
        button.addAction(UIAction(
            handler: { [weak self] _ in
            self?.viewModel.excuteLogin()
        }), for: .touchUpInside)
        return button
    }()

    init(viewModel: LoginViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("\(#function) has not been implemented")
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.addSubview(githubLoginButton)
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        githubLoginButton.snp.makeConstraints {
            $0.center.equalTo(view.safeAreaLayoutGuide)
        }
    }
}
