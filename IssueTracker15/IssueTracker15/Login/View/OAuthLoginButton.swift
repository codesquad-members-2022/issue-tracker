//
//  OAuthLoginButton.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/16.
//

import UIKit

enum LoginType: String {
    case gitHub = "GitHub 계정으로 로그인"
    case apple = "Apple 계정으로 로그인"
}

final class OAuthLoginButton: UIButton, ViewBindable {

    override init(frame: CGRect) {
        super.init(frame: frame)
        setUp()
    }
    
    @available(*, unavailable) required init?(coder: NSCoder) {
        fatalError("init with coder is unavailable")
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        super.touchesBegan(touches, with: event)
        sendAction(nil)
    }
    
    var vc: ViewBinding?
    
    func sendAction(_ param: Any?) {
        // GitHubLogin과 Apple로그인을 구분
        vc?.inputViewEvent(self, LoginType(rawValue: titleLabel?.text ?? ""))
    }
    
    func receive(_ responseData: Any) {  }
    
    func setVC(_ binding: ViewBinding) {
        self.vc = binding
    }
    
    private func setUp() {
        var config = UIButton.Configuration.filled()
        config.contentInsets = .init(top: 16, leading: 32, bottom: 16, trailing: 32)
        config.baseBackgroundColor = .black
        config.background.cornerRadius = 16.0
        config.imagePlacement = .leading
        self.configuration = config
    }
    
    func setServiceName(title: String) {
        var attributedTitle = AttributedString(title)
        attributedTitle.font = .systemFont(ofSize: 16.0, weight: .bold)
        self.configuration?.attributedTitle = attributedTitle
    }
    
    func setServiceImage(image: UIImage?) {
        self.configuration?.imagePadding = 8.0
        self.configuration?.image = image
    }
}
