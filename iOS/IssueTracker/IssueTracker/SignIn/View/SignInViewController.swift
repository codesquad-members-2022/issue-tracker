//
//  SignInViewController.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/15.
//

import UIKit

final class SignInViewController: UIViewController {
    private lazy var signInView = SignInView(frame: view.frame)
    private var viewModel: SignInViewModelProtocol

    init(viewModel: SignInViewModelProtocol) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
        bind(to: viewModel)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view = signInView
    }
}

// MARK: - Private Method
private extension SignInViewController {
    func bind(to viewModel: SignInViewModelProtocol) {
        signInView.setGitHubSignInButtonAction(UIAction { [weak self] _ in
            self?.viewModel.requestOAuthCode()
        })

        viewModel.buttonAction.bind(on: self) { url in
            guard let url = url else { return }
            UIApplication.shared.open(url)
        }

        viewModel.errorAction.bind(on: self) { [weak self] message in
            self?.showAlert(of: message)
        }

        viewModel.presentAction.bind(on: self) { [weak self] _ in
            self?.presentTabBarController()
        }
    }

    func showAlert(of errorMessage: String) {
        let alert = UIAlertController(title: "다시 로그인해주세요.", message: errorMessage, preferredStyle: .alert)
        let alertAction = UIAlertAction(title: "OK", style: .cancel)
        alert.addAction(alertAction)
        present(alert, animated: true)
    }

    func presentTabBarController() {
        let tabBarController = TabBarController()
        tabBarController.modalPresentationStyle = .fullScreen
        present(tabBarController, animated: true)
    }
}
