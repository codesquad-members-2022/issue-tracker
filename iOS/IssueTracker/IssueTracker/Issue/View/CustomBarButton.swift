//
//  CustomBarButton.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/16.
//

import UIKit

final class CustomBarButton: UIButton {
    override init(frame: CGRect) {
        super.init(frame: frame)
        configuration = UIButton.Configuration.plain()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setConfiguration(title: String, imageSystemName: String, imagePlacement: NSDirectionalRectEdge) {
        var attributedTitle = AttributedString(title)
        attributedTitle.font = UIFont(name: "SFProDisplay-Regualr", size: 17)
        configuration?.attributedTitle = attributedTitle

        configuration?.image = UIImage(systemName: imageSystemName)
        configuration?.imagePlacement = imagePlacement
        configuration?.imagePadding = 4
    }
}
