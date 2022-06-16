//
//  UIView+Extension.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/15.
//

import UIKit

extension UIView {
    func addSubviews(_ views: UIView...) {
        views.forEach { view in
            addSubview(view)
        }
    }
}
