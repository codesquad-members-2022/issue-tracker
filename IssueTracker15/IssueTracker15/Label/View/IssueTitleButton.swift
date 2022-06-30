//
//  IssueTitleButton.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/29.
//

import UIKit

final class IssueTitleButton: UIButton {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUp()
    }
    
    @available (*, unavailable) required init?(coder: NSCoder) {
        fatalError("init with coder is unavailable")
    }
    
    private func setUp() {
        var config = UIButton.Configuration.plain()
        config.contentInsets = NSDirectionalEdgeInsets(top: 8.0, leading: 8.0, bottom: 8.0, trailing: 8.0)
        config.background.cornerRadius = self.frame.height / 2
        self.configuration = config
    }
}

struct IssueLabel {
    let title: String
    let description: String
}
