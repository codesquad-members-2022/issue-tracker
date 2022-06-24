//
//  EditingIssueViewNavigationItems.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/23.
//

import UIKit

struct EditingIssueViewNavigationItems {
    let selectContentViewSegment: UISegmentedControl = {
        let segment = UISegmentedControl(items: ["마크다운", "미리보기"])
        segment.selectedSegmentTintColor = .white
        segment.selectedSegmentIndex = 0

        return segment
    }()

    let saveButton: CustomBarButton = {
        let customButton = CustomBarButton()
        customButton.setConfiguration(title: "저장",
                                      imageSystemName: "plus",
                                      imagePlacement: .trailing)
        return customButton
    }()

    let cancelButton: CustomBarButton = {
        let customButton = CustomBarButton()
        customButton.setConfiguration(title: "취소",
                                      imageSystemName: "chevron.backward",
                                      imagePlacement: .leading)
        return customButton
    }()
}
