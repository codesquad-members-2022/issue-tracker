//
//  LoginViewController.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import UIKit
import AuthenticationServices


class LoginViewController: UIViewController {
    
    @IBAction func githubLoginButtonTapped(_ sender: Any) {
        loginViewModel.requestGithubLogin()
    }
    
    private let loginViewModel: LoginViewModel = LoginViewModel()
    
    var issueViewModels: Observable<[IssueTableCellViewModel]?> = Observable(nil)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bindToViewModel()
    }
    
    private func bindToViewModel() {
        loginViewModel.authorizationStatus.bind { [weak self] status in
            switch status {
            case .authorized:
                let alert = Alert.loginSuccess.controller { _ in self?.gotoHome() }
                DispatchQueue.main.async {
                    self?.present(alert, animated: true)
                }
            case .failed:
                let alert = Alert.loginFailure.controller(handler: nil)
                DispatchQueue.main.async {
                    self?.present(alert, animated: true)
                }
            case .none:
                return
            }
        }
    }
    
    private func gotoHome() {
        self.performSegue(withIdentifier: "mainSegue", sender: nil)
    }
}

extension LoginViewController {
    enum Alert {
        case loginSuccess
        case loginFailure

        func controller(handler: ((UIAlertAction) -> Void)?) -> UIAlertController {
            let success = self.configuration(handler: handler)
            return UIAlertController.makeAlert(success)
        }
        
        func configuration(handler: ((UIAlertAction) -> Void)?) -> AlertConfiguration {
            switch self {
            case .loginSuccess:
                return AlertConfiguration(
                    title: "Github 로그인에 성공했습니다.",
                    message: "메인 화면으로 이동합니다.",
                    handler: handler)
            case .loginFailure:
                return AlertConfiguration(
                    title: "Github 로그인에 실패했습니다.",
                    message: "다시 시도해주세요.",
                    handler: handler)
            }
        }
    }
}
