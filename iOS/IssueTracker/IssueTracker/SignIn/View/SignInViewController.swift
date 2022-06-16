//
//  SignInViewController.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/15.
//

import UIKit

final class SignInViewController: UIViewController {
    private lazy var signInView = SignInView(frame: view.frame)

    init() {
        super.init(nibName: nil, bundle: nil)
        setObserver()
        setGitHubSignInButtonAction()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        view = signInView
    }
}

// MARK: - Private Method
private extension SignInViewController {
    func setObserver() {
        NotificationCenter.default.addObserver(forName: SceneDelegate.NotificationNames.didGetSignInError, object: nil, queue: nil) { [weak self] notification in
            let alert = UIAlertController(title: "다시 로그인 해주세요", message: notification.description, preferredStyle: .alert)
            let alertAction = UIAlertAction(title: "OK", style: .cancel)
            alert.addAction(alertAction)
            DispatchQueue.main.async {
                self?.present(alert, animated: true)
            }
        }
    }

    func setGitHubSignInButtonAction() {
        let gitHubSignInButtonAction = UIAction { _ in
            SignInManager.shared.requestCode { result in
                switch result {
                case let .success(url):
                    DispatchQueue.main.async {
                        UIApplication.shared.open(url)
                    }
                case let .failure(error):
                    print(error)
                }
            }
        }

        signInView.setGitHubSignInButtonAction(gitHubSignInButtonAction)
    }
}
