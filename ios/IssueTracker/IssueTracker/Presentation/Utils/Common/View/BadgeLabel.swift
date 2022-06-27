//
//  BadgeLabel.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/24.
//

import UIKit

final class BadgeLabel: UILabel {
    var padding = UIEdgeInsets.zero

    override func drawText(in rect: CGRect) {
        let badgeRect = rect.inset(by: padding)
        super.drawText(in: badgeRect)
    }

    override var intrinsicContentSize: CGSize {
        var contentSize = super.intrinsicContentSize
        contentSize.height += padding.top + padding.bottom
        contentSize.width += padding.left + padding.right
        return contentSize
    }
}
