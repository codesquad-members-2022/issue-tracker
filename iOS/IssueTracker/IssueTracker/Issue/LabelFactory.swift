//
//  LabelFactory.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/24.
//

import Foundation
import UIKit

struct LabelFactory {
    static func createLabel(_ label: Label) -> PaddingLabel {
        let paddingLabel = PaddingLabel()
        paddingLabel.translatesAutoresizingMaskIntoConstraints = false
        paddingLabel.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        paddingLabel.setEdgeInset(top: 4, bottom: 4, left: 16, right: 16)
        paddingLabel.clipsToBounds = true
        paddingLabel.text = label.title
        paddingLabel.backgroundColor = UIColor(hex: label.backgroundColor)

        return paddingLabel
    }
}
