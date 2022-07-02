//
//  EditingIssueTextViewDelegate.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/23.
//

import UIKit

final class EditingIssueTextViewDelegate: NSObject, UITextViewDelegate {
    static let placeholder = "코멘트는 여기에 작성하세요."

    private var onUpdateText: (String) -> Void = { _ in }

    func setTextViewAction(_ action: @escaping (String) -> Void) {
        onUpdateText = action
    }

    func textViewDidBeginEditing(_ textView: UITextView) {
        let isEditedTextEmpty = textView.text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty
        let isStartEditing = textView.text == Self.placeholder

        if isEditedTextEmpty {
            textView.textColor = .gray
            textView.text = Self.placeholder
        } else if isStartEditing {
            textView.textColor = .black
            textView.text = nil
        }
    }

    func textViewDidChange(_ textView: UITextView) {
        onUpdateText(textView.text)
    }

    func textViewDidEndEditing(_ textView: UITextView) {
        let isEditedTextEmpty = textView.text.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty

        if isEditedTextEmpty {
            textView.textColor = .gray
            textView.text = Self.placeholder
        }
    }
}
