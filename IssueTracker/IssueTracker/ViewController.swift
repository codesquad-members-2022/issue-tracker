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
            self.requestLogin()
        }))
        return button
    }()
    
    private func requestLogin() {
        let scope = "repo,user"
        let urlString = "https://github.com/login/oauth/authorize"
        guard var urlComponents = URLComponents(string: urlString) else {
           return
        }
        
        do {
            let clientId = try PrivateStorage().getClientId()
            urlComponents.queryItems = [
                URLQueryItem(name: "client_id", value: clientId),
                URLQueryItem(name: "scope", value: scope),
            ]
            
            if let url = urlComponents.url {
                UIApplication.shared.open(url) // 새 사파리 창을 열기
            }
        } catch(let error) {
            print(error.localizedDescription)
        }
        

    }
}

