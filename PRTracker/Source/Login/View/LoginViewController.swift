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
        loginViewModel.requestGithubLogin()
    }
    
    private let loginViewModel: LoginViewModel = LoginViewModel()
    
    
    private lazy var loginSuccessAlertConfiguration = AlertConfiguration(
        title: "Github 로그인에 성공했습니다.",
        message: "메인 화면으로 이동합니다.",
        handler: { [weak self] _ in self?.gotoHome()})
    
    private lazy var loginFailureAlertConfiguration = AlertConfiguration(
        title: "Github 로그인에 실패했습니다.",
        message: "다시 시도해주세요.",
        handler: nil)
    
    var issueViewModels: Observable<[IssueTableCellViewModel]?> = Observable(nil)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bindToViewModel()
    }
    
    private func bindToViewModel() {
        loginViewModel.authorizationStatus.bind { [weak self] status in
            var alertConfig = AlertConfiguration(title: "", message: "", handler: nil)
            
            switch status {
            case .authorized:
                guard let success = self?.loginSuccessAlertConfiguration else { return }
                alertConfig = success
            case .failed:
                guard let failure = self?.loginFailureAlertConfiguration else { return }
                alertConfig = failure
            case .none:
                return
            }
            
            DispatchQueue.main.async {
                let alert = UIAlertController.makeAlert(alertConfig)
                self?.present(alert, animated: true)
            }
        }
    }
    
    private func gotoHome() {
        self.performSegue(withIdentifier: "mainSegue", sender: nil)
    }
}
