//
//  LoginViewController.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/14.
//

import UIKit

final class LoginViewController: UIViewController {
    static func create(with viewModel: LoginViewModel) -> LoginViewController {
        let viewController = LoginViewController()
        viewController.viewModel = viewModel
        return viewController
    }

    private let loginView = LoginView()
    private var viewModel: LoginViewModel?

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        layout()
        bind()
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    private func layout() {
        view.addSubview(loginView)

        loginView.snp.makeConstraints {
            $0.edges.equalToSuperview()
        }
    }

    private func bind() {
        guard let viewModel = viewModel else {
            return
        }

        viewModel.output.isAuthenticated.bind { result in
            if result == true {
                viewModel.showMainScene()
            }
        }
    }
}
