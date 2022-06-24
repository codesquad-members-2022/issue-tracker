//
//  IssueViewNavigationItems.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/23.
//

import Foundation

struct IssueViewNavigationItems {
    let filterButton: CustomBarButton = {
        let customButton = CustomBarButton()
        customButton.setConfiguration(title: "filter", imageSystemName: "line.3.horizontal.decrease", imagePlacement: .leading)
        return customButton
    }()

    let selectButton: CustomBarButton = {
        let customButton = CustomBarButton()
        customButton.setConfiguration(title: "선택", imageSystemName: "checkmark.circle", imagePlacement: .trailing)
        return customButton
    }()
}
