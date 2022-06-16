//
//  OAuthLoginButton.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/16.
//

import UIKit

final class OAuthLoginButton: UIButton {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUp()
    }
    
    @available(*, unavailable) required init?(coder: NSCoder) {
        fatalError("init with coder is unavailable")
    }
    
    private func setUp() {
        var config = UIButton.Configuration.filled()
        config.contentInsets = .init(top: 8, leading: 32, bottom: 8, trailing: 32)
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
