//
//  CustomBarButton .swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/17.
//

import UIKit

final class CustomBarButton {
    private(set) var left: UIButton = {
        if #available(iOS 15.0, *) {
            var configuration = UIButton.Configuration.plain()
            var titleAttributedString = AttributedString.init("필터")
            titleAttributedString.font = .systemFont(ofSize: 17.0, weight: .light)
            configuration.attributedTitle = titleAttributedString
            configuration.image = UIImage(systemName: "line.3.horizontal.decrease.circle")
            configuration.imagePadding = 10
            let button = UIButton(configuration: configuration)
            return button
        }
        
        let button = UIButton()
        button.setTitle("필터", for: .normal)
        button.setImage(UIImage(named: "line.3.horizontal.decrease.circle"), for: .normal)
        button.frame = CGRect(x: 0, y: 0, width: 30, height: 30)
        button.sizeToFit()
        return button
    }()
    
    private(set) var right: UIButton = {
        if #available(iOS 15.0, *) {
            var configuration = UIButton.Configuration.plain()
            var titleAttributedString = AttributedString.init("선택")
            titleAttributedString.font = .systemFont(ofSize: 17.0, weight: .light)
            configuration.attributedTitle = titleAttributedString
            configuration.image = UIImage(systemName: "checkmark.circle")
            configuration.imagePadding = 10
            let button = UIButton(configuration: configuration)
            return button
        }
        
        let button = UIButton()
        button.setTitle("선택", for: .normal)
        button.setImage(UIImage(named: "checkmark.circle"), for: .normal)
        button.frame = CGRect(x: 0, y: 0, width: 30, height: 30)
        button.sizeToFit()
        return button
    }()
}
