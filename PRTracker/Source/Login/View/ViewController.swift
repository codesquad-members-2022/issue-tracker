//
//  ViewController.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import UIKit
import AuthenticationServices

class ViewController: UIViewController {

    @IBOutlet weak var githubLoginButton: UIButton!
    @IBOutlet weak var appleLoginButton: UIButton!
    
    @IBAction func emaliLoginButtonTapped(_ sender: Any) {
        // 임시적으로 이메일 로그인 버튼 클릭 시 메인 화면으로 이동하도록 구현
        DispatchQueue.main.async {
            self.performSegue(withIdentifier: "loginSegue", sender: nil)
        }
    }
    
    @IBAction func githubLoginButtonTapped(_ sender: Any) {
        viewModel.requestGithubLogin()
    }
    
    private let viewModel: LoginViewModel = LoginViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
