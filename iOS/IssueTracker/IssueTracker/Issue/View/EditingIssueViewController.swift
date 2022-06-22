//
//  EditingIssueViewController.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/21.
//

import SnapKit

final class EditingIssueViewController: UIViewController {
    private let selectContentViewSegment: UISegmentedControl = {
        let segment = UISegmentedControl(items: ["마크다운", "미리보기"])
        segment.selectedSegmentTintColor = .white
        segment.selectedSegmentIndex = 0
        return segment
    }()

    private let saveButton: CustomBarButton = {
        let customButton = CustomBarButton()
        customButton.setConfiguration(title: "저장", imageSystemName: "plus", imagePlacement: .trailing)
        return customButton
    }()

    private let cancelButton: CustomBarButton = {
        let customButton = CustomBarButton()
        customButton.setConfiguration(title: "취소", imageSystemName: "chevron.backward", imagePlacement: .leading)
        return customButton
    }()

    private lazy var editingIssueView = EditingIssueView(frame: view.bounds)

    override func viewDidLoad() {
        super.viewDidLoad()
        view = editingIssueView
        setDelegate()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        setNavigationBar()
    }
}

// MARK: - private methods
private extension EditingIssueViewController {
    func setDelegate() {
        editingIssueView.setContentTextViewDelegate(self)
        editingIssueView.setTitleTextFieldDelegate(self)
    }

    func setNavigationBar() {
        navigationItem.largeTitleDisplayMode = .never
        navigationItem.titleView = selectContentViewSegment
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: saveButton)
        navigationItem.rightBarButtonItem?.isEnabled = false
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: cancelButton)
        cancelButton.addAction(UIAction {_ in
            self.navigationController?.popViewController(animated: true)
        }, for: .touchUpInside)
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
        } else if !editingIssueView.isTitleTextFieldEmpty {
            navigationItem.rightBarButtonItem?.isEnabled = true
        } else {
            navigationItem.rightBarButtonItem?.isEnabled = false
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
