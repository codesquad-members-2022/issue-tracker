//
//  LoginViewController.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import UIKit
import AuthenticationServices

class LoginViewController: UIViewController {

    @IBOutlet weak var githubLoginButton: UIButton!
    
    @IBAction func emaliLoginButtonTapped(_ sender: Any) {
    }
    
    @IBAction func githubLoginButtonTapped(_ sender: Any) {
        viewModel.requestGithubLogin()
    }
    
    private let viewModel: LoginViewModel = LoginViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
