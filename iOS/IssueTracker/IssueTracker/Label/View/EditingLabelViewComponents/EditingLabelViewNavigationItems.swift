//
//  EditingLabelViewNavigationItems.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import UIKit

struct EditingLabelViewNavigationItems {
    let cancelButton: CustomBarButton = {
        let button = CustomBarButton()
        button.setConfiguration(title: "취소",
                                imageSystemName: "chevron.backward",
                                imagePlacement: .leading)
        return button
    }()

    let saveButton: CustomBarButton = {
        let button = CustomBarButton()
        button.setConfiguration(title: "저장",
                                imageSystemName: "",
                                imagePlacement: .all)
        return button
    }()
}
