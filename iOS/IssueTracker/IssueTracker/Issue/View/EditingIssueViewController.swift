//
//  EditingIssueViewController.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/21.
//

import SnapKit

final class EditingIssueViewController: UIViewController {
    private lazy var editingIssueView = EditingIssueView(frame: view.bounds)
    private let navigationItems = EditingIssueViewNavigationItems()

    override func viewDidLoad() {
        super.viewDidLoad()
        view = editingIssueView
        setDelegate()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        setNavigationItems()
    }
}

// MARK: - private methods
private extension EditingIssueViewController {
    func setDelegate() {
        editingIssueView.setContentTextViewDelegate(self)
        editingIssueView.setTitleTextFieldDelegate(self)
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
        editingIssueView.changeContentView(to: navigationItems.selectContentViewSegment.selectedSegmentIndex)
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
            !editingIssueView.isTitleTextFieldEmpty {
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
        if !editingIssueView.isContentTextEmpty &&
            !textField.text!.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty {
            navigationItem.rightBarButtonItem?.isEnabled = true
        } else {
            navigationItem.rightBarButtonItem?.isEnabled = false
        }
    }
}
