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
        signInView.setGitHubSignInButtonAction(UIAction(handler: { _ in
            SignInManager.shared.requestCode { result in
                switch result {
                case let .success(url):
                    UIApplication.shared.open(url)
                case let .failure(error):
                    print(error)
                }
            }
        }))
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
