//
//  UIImage+SFSymbol.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/22.
//

import UIKit

// SF Symbol 에서 기본으로 제공하는 UIImage를 .init(systemName:) 으로 편리하게 가져오고 관리하기 위해 type extension만 따로 분리하였습니다.
extension UIImage {
    static var filterButtonImage: UIImage? {
        UIImage(systemName: "line.3.horizontal.decrease")
    }
    
    static var checkButtonImage: UIImage? {
        UIImage(systemName: "checkmark.circle")
    }
    
    static var checkButtonImageFilled: UIImage? {
        UIImage(systemName: "checkmark.circle.fill")
    }
}
