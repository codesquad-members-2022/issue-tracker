//
//  HamburgerButton.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/30.
//

import UIKit

final class HamburgerButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        configuration = UIButton.Configuration.filled()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setConfiguration(systemImageName: String, backgroundColor: UIColor) {
        var configuration = UIButton.Configuration.filled()

        configuration.image = UIImage(systemName: systemImageName)
        configuration.baseBackgroundColor = backgroundColor
        configuration.cornerStyle = .capsule

        self.configuration = configuration
    }
}
