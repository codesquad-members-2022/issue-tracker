//
//  ViewController.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var githubLoginButton: UIButton!
    @IBOutlet weak var appleLoginButton: UIButton!
    
    @IBAction func githubLoginButtonTapped(_ sender: Any) {
        // TODO: OAuthManager에게 인증 요청
        viewModel.requestGithubLogin()
    }
    
    @IBAction func appleLoginButtonTapped(_ sender: Any) {
        // TODO: OAuthManager에게 인증 요청
        // 임시적으로 애플 로그인 버튼 클릭 시 메인 화면으로 이동하도록 구현
        DispatchQueue.main.async {
            self.performSegue(withIdentifier: "loginSegue", sender: nil)
        }
    }
    
    private let viewModel: LoginViewModel = LoginViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
