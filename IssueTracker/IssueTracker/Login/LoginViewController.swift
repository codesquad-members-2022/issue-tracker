//
//  LoginViewController.swift
//  IssueTracker
//
//  Created by Sujin Jin on 2022/06/13.
//

import UIKit
import SnapKit
import Alamofire

// 이 VC에서 Coordinator에 위임할 작업
protocol LoginViewControllerDelegate {
    func login()
}

class LoginViewController: UIViewController {

    var delegate: LoginViewControllerDelegate?
    
    private var model: LoginModel
    
    init(model: LoginModel) {
        self.model = model
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        self.model = LoginModel(environment: .init(requestCode: { completion in 
        }))
        super.init(coder: coder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    func setupView() {
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
        let button = UIButton(configuration: configuration, primaryAction: UIAction(handler: { [weak self] _ in
            self?.loginButtonTapped()
        }))
        return button
    }()
    
    private func touchedLoginButton() {
        model.requestCode { result in
            switch result {
            case .success(let url):
                UIApplication.shared.open(url)
            case .failure(let error):
                // TODO: - 로그인 하지 못했을때 에러처리
                print(error)
            }
        }
    }
    
    // 위의 메서드를 아래로 변경
    func loginButtonTapped() {
        self.delegate?.login()
    }
}

