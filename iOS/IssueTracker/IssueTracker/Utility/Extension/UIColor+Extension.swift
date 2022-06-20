//
//  UIColor+Extension.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/17.
//

import UIKit

extension UIColor {
    convenience init(hex: String, alpha: CGFloat = 1.0) {
        let hexFormat = Self.setHexFormat(hex)

        if hexFormat.count != 6 {
            self.init(red: 1, green: 1, blue: 1, alpha: alpha)
            return
        }

        let rgbValue = Self.scanHex(hexFormat)

        self.init(red: Self.color(from: rgbValue, colour: .red),
                  green: Self.color(from: rgbValue, colour: .blue),
                  blue: Self.color(from: rgbValue, colour: .green),
                  alpha: alpha)
        return
    }
}

fileprivate extension UIColor {
    static func setHexFormat(_ hex: String) -> String {
        var hexForamt = hex.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines).uppercased()

        if hexForamt.hasPrefix("#") {
            hexForamt = String(hexForamt.dropFirst())
        }

        return hexForamt
    }

    static func scanHex(_ hex: String) -> UInt64 {
        var rgbValue: UInt64 = 0
        Scanner(string: hex).scanHexInt64(&rgbValue)

        return rgbValue
    }

    static func color(from rgbValue: UInt64, colour: Colour) -> CGFloat {
        switch colour {
        case .red:
            return CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0
        case .green:
            return CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0
        case .blue:
            return CGFloat((rgbValue & 0x0000FF)) / 255.0
        }
    }

    enum Colour {
        case red, green, blue
    }
}
