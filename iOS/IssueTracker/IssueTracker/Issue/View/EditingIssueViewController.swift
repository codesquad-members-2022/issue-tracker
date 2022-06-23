//
//  EditingIssueViewController.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/21.
//

import SnapKit

final class EditingIssueViewController: UIViewController {
    private let titleViewComponents = EditingIssueTitleViewComponents()
    private let contentViewComponents = EditingIssueContentViewComponents()
    private let navigationItems = EditingIssueViewNavigationItems()

    override func viewDidLoad() {
        super.viewDidLoad()
        view.addSubviews(titleViewComponents, contentViewComponents)
        setDelegate()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        view.backgroundColor = .white
        setNavigationItems()
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        let screenSize = UIScreen.main.bounds.size

        titleViewComponents.snp.makeConstraints { make in
            make.top.equalTo(view.safeAreaLayoutGuide)
            make.height.equalTo(43.5/812 * screenSize.height)
            make.leading.equalTo(view).offset(16/375 * screenSize.width)
            make.trailing.equalTo(view).inset(16/375 * screenSize.width)
        }

        contentViewComponents.snp.makeConstraints { make in
            make.top.equalTo(titleViewComponents.snp.bottom)
            make.leading.trailing.equalTo(titleViewComponents)
            make.height.equalTo(411/812 * screenSize.height)
        }
        
    }
}

// MARK: - private methods
private extension EditingIssueViewController {
    func setDelegate() {
        titleViewComponents.titleTextField.delegate = self
        contentViewComponents.editableContentTextView.delegate = self
    }

    func setNavigationItems() {
        navigationItem.largeTitleDisplayMode = .never
        navigationItem.titleView = navigationItems.selectContentViewSegment
        navigationItems.selectContentViewSegment.addAction(UIAction { [weak self] _ in
            self?.didSegmentValueChanged()
        }, for: .valueChanged)

        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: navigationItems.saveButton)
        navigationItem.rightBarButtonItem?.isEnabled = false

        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: navigationItems.cancelButton)
        navigationItems.cancelButton.addAction(UIAction { [weak self] _ in
            self?.navigationController?.popViewController(animated: true)
        }, for: .touchUpInside)
    }

    func didSegmentValueChanged() {
        let selectedIndex = navigationItems.selectContentViewSegment.selectedSegmentIndex

        selectedIndex == 0 ?
        contentViewComponents.showEditableContentTextView() : contentViewComponents.showPreviewTextView()
    }
}

// MARK: - UITextViewDelegate for placeholder
extension EditingIssueViewController: UITextViewDelegate {
    private var placeholder: String {
        return "코멘트는 여기에 작성하세요."
    }

    func textViewDidBeginEditing(_ textView: UITextView) {
        if textView.text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty {
            textView.textColor = .gray
            textView.text = placeholder
        } else if textView.text == placeholder {
            textView.textColor = .black
            textView.text = nil
        }
    }

    func textViewDidChange(_ textView: UITextView) {
        if !textView.text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty &&
            !(titleViewComponents.titleTextField.text?.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty ?? true) {
            navigationItem.rightBarButtonItem?.isEnabled = true
        } else {
            navigationItem.rightBarButtonItem?.isEnabled = false
        }
    }

    func textViewDidEndEditing(_ textView: UITextView) {
        if textView.text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty {
            textView.textColor = .gray
            textView.text = placeholder
        }
    }
}

// MARK: - UITextFieldDelegate for enabling Save Button
extension EditingIssueViewController: UITextFieldDelegate {
    func textFieldDidBeginEditing(_ textField: UITextField) {
        if !contentViewComponents.editableContentTextView.text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty &&
            !textField.text!.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty {
            navigationItem.rightBarButtonItem?.isEnabled = true
        } else {
            navigationItem.rightBarButtonItem?.isEnabled = false
        }
    }
}
