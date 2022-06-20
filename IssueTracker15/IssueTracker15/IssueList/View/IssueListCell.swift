//
//  IssueListCell.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import UIKit

/// Delegate 로 대체할 수 있는 기능들이므로, ViewBindable 구현하지 않음
class IssueListCell: UICollectionViewCell {
    
    var issueDTO: IssueDTO?
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
    }
}

extension UICollectionViewCell {
    static var reuseIdentifier: String {
        return String(describing: Self.self)
    }
}
