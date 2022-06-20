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
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view = signInView
        bind(to: viewModel)
    }
}

// MARK: - Private Method
private extension SignInViewController {
    func bind(to viewModel: SignInViewModelProtocol) {
        signInView.setGitHubSignInButtonAction(UIAction { [weak self] _ in
            self?.viewModel.didSelect()
        })

        viewModel.error.bind(on: self) { [weak self] message in
            self?.showAlert(of: message)
        }

        viewModel.setPresentAction { [weak self] in
            self?.presentTabBarController()
        }
    }

    func showAlert(of errorMessage: String) {
        DispatchQueue.main.async { [weak self] in
            let alert = UIAlertController(title: "다시 로그인해주세요.", message: errorMessage, preferredStyle: .alert)
            let alertAction = UIAlertAction(title: "OK", style: .cancel)
            alert.addAction(alertAction)
            self?.present(alert, animated: true)
        }
    }

    func presentTabBarController() {
        DispatchQueue.main.async { [weak self] in
            let tabBarController = TabBarController()
            tabBarController.modalPresentationStyle = .fullScreen
            self?.present(tabBarController, animated: true)
        }
    }
}
