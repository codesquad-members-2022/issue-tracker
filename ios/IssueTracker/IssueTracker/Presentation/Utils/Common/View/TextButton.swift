//
//  TextButton.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/29.
//

import UIKit

final class TextButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        configureUI()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        configureUI()
    }

    private func configureUI() {
        var configuration = UIButton.Configuration.plain()
        configuration.title = "Button"
        self.configuration = configuration
    }

    func setSymbol(_ symbol: UIImage?, on placement: NSDirectionalRectEdge = .leading) {
        configuration?.image = symbol
        configuration?.imagePlacement = placement
    }
}
