//
//  EditingLabelView.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/29.
//

import UIKit

final class EditingLabelView: UIStackView {
    private let titleTextField: TextFieldWithTitleView = {
        let textField = TextFieldWithTitleView()
        textField.setText(title: "제목", placeholder: "필수 사항")
        return textField
    }()

    private let descriptionTextField: TextFieldWithTitleView = {
        let textField = TextFieldWithTitleView()
        textField.setText(title: "설명", placeholder: "선택 사항")
        return textField
    }()

    private let selectingLabelBackgroundColorView = SelectingLabelBackgroundColorView()

    private let editedLabelPreviewView = EditedLabelPreviewView()

    override init(frame: CGRect) {
        super.init(frame: frame)
        let screenSize = UIScreen.main.bounds.size

        translatesAutoresizingMaskIntoConstraints = false
        axis = .vertical
        spacing = 11/812 * screenSize.height
        setSubviewsLayout()
    }

    @available(*, unavailable)
    required init(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

private extension EditingLabelView {
    func setSubviewsLayout() {
        setSelectingLabelComponentsViewLayout()
        setPreviewViewLayout()
    }

    func setSelectingLabelComponentsViewLayout() {
        let screenSize = UIScreen.main.bounds.size
        let selectingLabelComponentsView = UIStackView()
        selectingLabelComponentsView.translatesAutoresizingMaskIntoConstraints = false
        selectingLabelComponentsView.axis = .vertical
        selectingLabelComponentsView.spacing = 11/812 * screenSize.height
        selectingLabelComponentsView.backgroundColor = .white

        addArrangedSubview(selectingLabelComponentsView)
        let deviderView0 = DeviderView()
        deviderView0.backgroundColor = .gray5
        let deviderView1 = DeviderView()
        deviderView1.backgroundColor = .gray5
        let deviderView2 = DeviderView()
        deviderView2.backgroundColor = .gray5
        let deviderView3 = DeviderView()
        deviderView3.backgroundColor = .gray5
        selectingLabelComponentsView.addArrangedSubviews(
            deviderView0, titleTextField,
            deviderView1, descriptionTextField,
            deviderView2, selectingLabelBackgroundColorView,
            deviderView3)
    }

    func setPreviewViewLayout() {
        addArrangedSubview(editedLabelPreviewView)
    }
}
