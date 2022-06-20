//
//  ViewController.swift
//  IssueTracker
//
//  Created by Sujin Jin on 2022/06/13.
//

import UIKit
import SnapKit
import Alamofire

class ViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        setupView()
    }
    
    private func setupView() {
        view.addSubview(loginButton)
        loginButton.snp.makeConstraints { make in
            make.center.equalToSuperview()
        }
    }
    
    private lazy var loginButton: UIButton = {
        
        var configuration = UIButton.Configuration.gray()
        var container = AttributeContainer()
        container.font = UIFont.boldSystemFont(ofSize: 20)
        configuration.attributedTitle = AttributedString("Github Login", attributes: container)
        
        configuration.cornerStyle = .capsule
        configuration.baseForegroundColor = UIColor.black
        configuration.buttonSize = .large
        configuration.image = UIImage(named: "GitHub-Mark")
        configuration.imagePadding = 8
        let button = UIButton(configuration: configuration, primaryAction: UIAction(handler: { _ in
            self.touchedLoginButton()
        }))
        return button
    }()
    
    private func touchedLoginButton() {
        NetworkManager.shared.requestCode { result in
            switch result {
            case .success(let url):
                UIApplication.shared.open(url)
            case .failure(let error):
                // TODO: - 로그인 하지 못했을때 에러처리
                print(error)
            }
        }
    }
}

