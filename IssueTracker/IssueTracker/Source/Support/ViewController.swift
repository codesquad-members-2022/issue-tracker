//
//  ViewController.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/13.
//

import UIKit
import SnapKit

class ViewController: UIViewController {

    private let githubLoginButton: UIButton = {
        let button = UIButton()
        button.setTitle("gitbutton", for: .normal)
        button.addAction(UIAction(handler: { _ in
            LoginManager.requestGithubAuthorize()
        }), for: .touchUpInside)
        return button
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        view.addSubview(githubLoginButton)

        githubLoginButton.snp.makeConstraints {
            $0.center.equalTo(view.safeAreaLayoutGuide)
        }
    }

}
