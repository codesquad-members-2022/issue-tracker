//
//  LabelViewNavigationItems.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/27.
//

import Foundation

struct LabelViewNavigationItems {
    let addButton: CustomBarButton = {
        let customButton = CustomBarButton()
        customButton.setConfiguration(title: "추가", imageSystemName: "plus", imagePlacement: .trailing)
        return customButton
    }()
}
