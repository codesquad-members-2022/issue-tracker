//
//  UIStackView+Extension.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/23.
//

import UIKit

extension UIStackView {
    func addArrangedSubviews(_ views: UIView...) {
        views.forEach { (view) in
            addArrangedSubview(view)
        }
    }
}
