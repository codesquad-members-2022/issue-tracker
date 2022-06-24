//
//  EditingIssueContentViewComponents.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/23.
//

import SnapKit
import SwiftyMarkdown

final class EditingIssueContentViewComponents: UIView {
    private let dividingView: UIView = {
        let view = UIView()
        view.translatesAutoresizingMaskIntoConstraints = false
        view.backgroundColor = .gray5
        return view
    }()

    let editableContentTextView: UITextView = {
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

    let previewTextView: UITextView = {
        let textView = UITextView()
        let screenSize = UIScreen.main.bounds.size
        textView.translatesAutoresizingMaskIntoConstraints = false
        textView.font = UIFont(name: "SFProDisplay-Regualr", size: 17/812.0 * screenSize.height)
        textView.alpha = 0
        textView.isEditable = false
        textView.isPagingEnabled = true
        return textView
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        addSubviews(dividingView, editableContentTextView, previewTextView)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        let screenSize = UIScreen.main.bounds.size

        dividingView.snp.makeConstraints { make in
            make.top.equalTo(self)
            make.height.equalTo(1)
            make.leading.equalTo(self).offset(4/375 * screenSize.width)
            make.trailing.equalTo(self).inset(4/375 * screenSize.width)
        }

        editableContentTextView.snp.makeConstraints { make in
            make.top.equalTo(dividingView.snp.bottom).offset(10.5/812 * screenSize.height)
            make.leading.trailing.equalTo(dividingView)
            make.bottom.equalTo(self)
        }

        previewTextView.snp.makeConstraints { make in
            make.edges.equalTo(editableContentTextView)
        }
    }

    func showEditableContentTextView() {
        UIView.animate(withDuration: 0.5) { [weak self] in
            self?.editableContentTextView.alpha = 1.0
            self?.previewTextView.alpha = 0
        }
    }

    func showPreviewTextView() {
        if editableContentTextView.text != "코멘트는 여기에 작성하세요." {
            previewTextView.attributedText = SwiftyMarkdown(string: editableContentTextView.text).attributedString()
        }
        UIView.animate(withDuration: 0.5) { [weak self] in
            self?.editableContentTextView.alpha = 0
            self?.previewTextView.alpha = 1.0
        }
    }
}
