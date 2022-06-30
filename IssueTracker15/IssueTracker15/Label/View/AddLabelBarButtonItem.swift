//
//  AddLabelButton.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/29.
//

import UIKit

final class AddLabelBarButtonItem: UIBarButtonItem, ViewBindable {
    var vc: ViewBinding?
    
    private lazy var buttonAction: UIAction = {
        let action = UIAction.init { [weak self] _ in
            guard let self = self else { return }
            self.vc?.inputViewEvent(self, nil)
        }
        return action
    }()
    
    func sendAction(_ param: Any?) { }
    func receive(_ responseData: Any) { }
    
    override init() {
        super.init()
        setUp()
    }
    
    @available(*, unavailable) required init?(coder: NSCoder) {
        fatalError("init with coder is unavailable")
    }
    
    private func setUp() {
        var config = UIButton.Configuration.plain()
        config.image = UIImage(systemName: "plus")
        config.imagePlacement = .trailing
        config.title = "추가 "
        
        let button = UIButton(configuration: config)
        button.addAction(buttonAction, for: .touchUpInside)
        self.customView = button
    }
}
