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

    private var viewModel: EditingLabelViewModelProtocol

    init(viewModel: EditingLabelViewModelProtocol) {
        self.viewModel = viewModel
        super.init(frame: .zero)
        let screenSize = UIScreen.main.bounds.size

        translatesAutoresizingMaskIntoConstraints = false
        axis = .vertical
        spacing = 11/812 * screenSize.height
        setSubviewsLayout()
        bind(to: viewModel)
    }

    @available(*, unavailable)
    required init(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func updatePreviewLabelText(with text: String?) {
        if let text = text,
           !text.isEmpty {
            editedLabelPreviewView.labelPreviewView.text = text
        } else {
            editedLabelPreviewView.labelPreviewView.text = "레이블"
        }
    }

    func updatePreviewLabelBackgroundColor(with backgroundColorText: String) {
        editedLabelPreviewView.labelPreviewView.backgroundColor = UIColor(hex: backgroundColorText)
        let isDarkMode = UIColor(hex: backgroundColorText).isDarkColor

        editedLabelPreviewView.labelPreviewView.textColor = isDarkMode ?
            .white : .black

        viewModel.setLabelDarkMode(with: isDarkMode)
    }

    func updateSelectedBackgroundLabel(with backgroundColorText: String) {
        selectingLabelBackgroundColorView.selectedBackgroundColorLabel.text = backgroundColorText
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

    func bind(to viewModel: EditingLabelViewModelProtocol) {
        viewModel.isOverFiftyCharactersInTitleText.bind(on: self) { [weak self] isOverFiftyCharactersInTitleText in
            if isOverFiftyCharactersInTitleText,
               let text = self?.titleTextField.editableTextField.text {
                self?.titleTextField.editableTextField.text = String(text.dropLast())
            }
        }
        selectingLabelBackgroundColorView.generateRandomColorButton.addAction(UIAction { _ in
            viewModel.didTouchGenerateRandomBackgroundColorButton()
        }, for: .touchUpInside)

        titleTextField.editableTextField.addAction(UIAction { [weak self] _ in
            
            viewModel.didChangedTitleTextField(text: self?.titleTextField.editableTextField.text)
        }, for: .editingChanged)

        descriptionTextField.editableTextField.addAction(UIAction { [weak self] _ in
            viewModel.didChangedDescriptionTextField(text: self?.descriptionTextField.editableTextField.text)
        }, for: .editingChanged)

        selectingLabelBackgroundColorView.generateRandomColorButton.addAction(UIAction { _ in
            viewModel.didTouchGenerateRandomBackgroundColorButton()
        }, for: .touchUpInside)
    }
}
