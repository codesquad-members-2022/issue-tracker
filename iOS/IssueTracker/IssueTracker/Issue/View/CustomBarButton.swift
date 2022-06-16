//
//  CustomBarButton.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/16.
//

import UIKit

final class CustomBarButton {
    
    let leftButton: UIButton = {
        var config = UIButton.Configuration.plain()
        config.image = UIImage(named: "filter")
        config.imagePadding = 10
        
        var titleAttribute = AttributedString.init("필터")
        titleAttribute.font = .systemFont(ofSize: 17.0, weight: .light)
        config.attributedTitle = titleAttribute
        
        let leftButton = UIButton(configuration: config)
        return leftButton
    }()
    
    let rightButton: UIButton = {
        var config = UIButton.Configuration.plain()
        config.image = UIImage(systemName: "checkmark.circle")
        config.imagePlacement = NSDirectionalRectEdge.trailing
        config.imagePadding = 10
        
        var titleAttribute = AttributedString.init("선택")
        titleAttribute.font = .systemFont(ofSize: 17.0, weight: .light)
        config.attributedTitle = titleAttribute
        
        let rightButton = UIButton(configuration: config)
        return rightButton
    }()
}
