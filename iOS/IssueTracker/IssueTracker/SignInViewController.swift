//
//  SignInViewController.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/15.
//

import UIKit

final class SignInViewController: UIViewController {
    private lazy var signInView = SignInView(frame: view.frame)

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        view = signInView
    }
}
