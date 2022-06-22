//
//  EditingIssueView.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/22.
//

import UIKit
import SwiftyMarkdown

final class EditingIssueView: UIView {
    private let titleStackView: UIStackView = {
        let stackView = UIStackView()
        stackView.translatesAutoresizingMaskIntoConstraints = false
        stackView.axis = .horizontal
        stackView.distribution = .fillProportionally
        return stackView
    }()

    private let titleLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "제목"
        label.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        return label
    }()

    private let titleTextField: UITextField = {
        let textField = UITextField()
        let screenSize = UIScreen.main.bounds.size
        textField.translatesAutoresizingMaskIntoConstraints = false
        textField.font = UIFont(name: "SFProDisplay-Regular", size: 17.0/812.0 * screenSize.height)
        textField.alpha = 1
        textField.autocorrectionType = .no
        textField.autocapitalizationType = .none
        return textField
    }()

    private let previewTextView: UITextView = {
        let textView = UITextView()
        let screenSize = UIScreen.main.bounds.size
        textView.translatesAutoresizingMaskIntoConstraints = false
        textView.font = UIFont(name: "SFProDisplay-Regualr", size: 17/812.0 * screenSize.height)
        textView.alpha = 0
        textView.isEditable = false
        textView.isPagingEnabled = true
        return textView
    }()

    private let dividingView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .gray5
        return view
    }()

    private let contentTextView: UITextView = {
        let textView = UITextView()
        let screenSize = UIScreen.main.bounds.size
        textView.translatesAutoresizingMaskIntoConstraints = false
        textView.font = UIFont(name: "SFProDisplay-Regular", size: 17.0/812.0 * screenSize.height)
        textView.text = "코멘트는 여기에 작성하세요."
        textView.textColor = .gray
        textView.autocorrectionType = .no
        textView.autocapitalizationType = .none
        return textView
    }()

    var isContentTextEmpty: Bool {
        return contentTextView.text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty ||
        contentTextView.text == "코멘트는 여기에 작성하세요."
    }

    var isTitleTextFieldEmpty: Bool {
        return titleTextField.text?.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty ?? false
    }

    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .white
        titleStackView.addArrangedSubview(titleLabel)
        titleStackView.addArrangedSubview(titleTextField)
        addSubviews(titleStackView, dividingView, contentTextView, previewTextView)
        addActionToTitleTextField()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    func setContentTextViewDelegate(_ delegate: UITextViewDelegate) {
        contentTextView.delegate = delegate
    }

    func setTitleTextFieldDelegate(_ delegate: UITextFieldDelegate) {
        titleTextField.delegate = delegate
    }

    func changeContentView(to selectedIndex: Int) {
        let unselectedIndex = (selectedIndex == 0) ? 1 : 0
        let contentViews = [contentTextView, previewTextView]

        if contentTextView.text != "코멘트는 여기에 작성하세요." {
            previewTextView.attributedText = SwiftyMarkdown(string: contentTextView.text).attributedString()
        }
        UIView.animate(withDuration: 0.5) {
            contentViews[selectedIndex].alpha = 1
            contentViews[unselectedIndex].alpha = 0
        }
    }
    
    override func layoutSubviews() {
        let screenSize = UIScreen.main.bounds.size

        titleStackView.snp.makeConstraints { make in
            make.top.equalTo(safeAreaLayoutGuide)
            make.height.equalTo(43.5/812 * screenSize.height)
            make.leading.equalTo(self).offset(16/375 * screenSize.width)
            make.trailing.equalTo(self).inset(16/375 * screenSize.width)
        }

        titleLabel.snp.makeConstraints { make in
            make.width.equalTo(64/375 * screenSize.width)
        }

        dividingView.snp.makeConstraints { make in
            make.top.equalTo(titleStackView.snp.bottom)
            make.trailing.equalTo(titleStackView).inset(4/375 * screenSize.width)
            make.leading.equalTo(titleStackView).offset(4/375 * screenSize.width)
            make.height.equalTo(1)
        }

        contentTextView.snp.makeConstraints { make in
            make.top.equalTo(dividingView.snp.bottom).offset(10.5/812 * screenSize.height)
            make.leading.trailing.equalTo(titleStackView)
            make.height.equalTo(410/812 * screenSize.height)
        }

        previewTextView.snp.makeConstraints { make in
            make.edges.equalTo(contentTextView)
        }
    }
}

private extension EditingIssueView {
    func addActionToTitleTextField() {
        let action = UIAction { [weak self] _ in
            guard let self = self else { return }
            self.titleTextField.delegate?.textFieldDidBeginEditing?(self.titleTextField)
        }

        titleTextField.addAction(action, for: .editingChanged)
    }
}
