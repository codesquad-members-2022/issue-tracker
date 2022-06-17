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
    
    @IBAction func githubLoginButtonTapped(_ sender: Any) {
        // TODO: OAuthManager에게 인증 요청
        viewModel.requestGithubLogin()
    }
    
    @IBAction func appleLoginButtonTapped(_ sender: Any) {
        let request = ASAuthorizationAppleIDProvider().createRequest()
        request.requestedScopes = [.fullName, .email]
        let controller = ASAuthorizationController(authorizationRequests: [request])
        let loginManager = AppleLoginManager()
        controller.delegate = loginManager
        controller.presentationContextProvider = self as? ASAuthorizationControllerPresentationContextProviding
        controller.performRequests()
    }
    
    private let viewModel: LoginViewModel = LoginViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
