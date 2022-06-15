//
//  LoginViewController.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/14.
//

import UIKit

class LoginViewController: UIViewController {
    private let loginView = LoignView()

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
        layout()
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    func layout() {
        view.addSubview(loginView)

        loginView.snp.makeConstraints {
            $0.edges.equalToSuperview()
        }
    }
}
