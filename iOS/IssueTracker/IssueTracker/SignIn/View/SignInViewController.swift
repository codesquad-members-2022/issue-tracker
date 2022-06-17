//
//  SignInViewController.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/15.
//

import UIKit

final class SignInViewController: UIViewController {
    private lazy var signInView = SignInView(frame: view.frame)
    private var viewModel: SignInViewModelProtocol? {
        didSet {
            viewModel?.buttonAction = { url in
                DispatchQueue.main.async {
                    UIApplication.shared.open(url)
                }
            }

            viewModel?.errorAction = { [weak self] errorDescription in
                let alert = UIAlertController(title: "다시 로그인 해주세요", message: errorDescription, preferredStyle: .alert)
                let alertAction = UIAlertAction(title: "OK", style: .cancel)
                alert.addAction(alertAction)
                DispatchQueue.main.async {
                    self?.present(alert, animated: true)
                }
            }

            viewModel?.presentAction = { [weak self] in
                DispatchQueue.main.async {
                    let tabBarController = TabBarController()
                    tabBarController.modalPresentationStyle = .fullScreen
                    self?.present(tabBarController, animated: true)
                }
            }
        }
    }

    init() {
        super.init(nibName: nil, bundle: nil)
        viewModel = SignInViewModel()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError()
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        view = signInView
        setGitHubSignInButtonAction()
    }
}

// MARK: - Private Method
private extension SignInViewController {
    func setGitHubSignInButtonAction() {
        signInView.setGitHubSignInButtonAction(UIAction { [weak self] _ in
            self?.viewModel?.requestOAuthCode()
        })
    }
}
