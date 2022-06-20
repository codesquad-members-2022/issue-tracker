//
//  LoginButton.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/16.
//

import UIKit

class LoginButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        layout()
    }

    @available(*, unavailable)
    required init?(coder _: NSCoder) {
        fatalError("Init with coder is unavailable")
    }

    private func layout() {
        var config = UIButton.Configuration.bordered()
        config.baseBackgroundColor = .white
        config.baseForegroundColor = .black
        config.background.strokeColor = .line
        config.cornerStyle = .medium
        config.imagePadding = 10
        config.imagePlacement = .leading
        configuration = config
    }
}
