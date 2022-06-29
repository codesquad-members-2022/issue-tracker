//
//  Label.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/24.
//

import UIKit

class Label: UILabel {
    private var padding = UIEdgeInsets.zero {
        didSet {
            invalidateIntrinsicContentSize()
        }
    }

    override func drawText(in rect: CGRect) {
        super.drawText(in: rect.inset(by: padding))
    }

    override var intrinsicContentSize: CGSize {
        var contentSize = super.intrinsicContentSize
        contentSize.height += padding.top + padding.bottom
        contentSize.width += padding.left + padding.right
        return contentSize
    }

    func setPadding(top: CGFloat = 0, left: CGFloat = 0, bottom: CGFloat = 0, right: CGFloat = 0) {
        padding = UIEdgeInsets(top: top, left: left, bottom: bottom, right: right)
    }
}
