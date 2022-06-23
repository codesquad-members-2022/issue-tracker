//
//  IntroViewController.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/22.
//

import UIKit

class IntroViewController: UIViewController {

    private let viewModel: LoginViewModel = LoginViewModel()
    
    private func gotoLogin() {
        DispatchQueue.main.async {
            self.performSegue(withIdentifier: "loginSegue", sender: nil)
        }
    }
    
    private func gotoHome() {
        DispatchQueue.main.async {
            self.performSegue(withIdentifier: "mainSegue", sender: nil)
        }
    }
    
    private func validateToken() {
        viewModel.hasValidToken { [weak self] hasValidToken in
            if hasValidToken {
                // TODO: 'home', 'main' 용어 통일 필요
                self?.gotoHome()
            } else {
                self?.gotoLogin()
            }
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        validateToken()
    }
}
