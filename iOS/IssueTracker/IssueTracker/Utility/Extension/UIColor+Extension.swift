//
//  UIColor+Extension.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/17.
//

import UIKit

extension UIColor {
    convenience init(hex: String, alpha: CGFloat = 1.0) {
        var hexFormat: String = hex.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines).uppercased()

        if hexFormat.hasPrefix("#") {
            hexFormat = String(hexFormat.dropFirst())
        }

        if hexFormat.count != 6 {
            self.init(red: 1, green: 1, blue: 1, alpha: alpha)
            return
        }

        var rgbValue: UInt64 = 0
        Scanner(string: hexFormat).scanHexInt64(&rgbValue)

        self.init(red: CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0,
                  green: CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0,
                  blue: CGFloat((rgbValue & 0x0000FF)) / 255.0,
                  alpha: alpha)
        return
    }
}
