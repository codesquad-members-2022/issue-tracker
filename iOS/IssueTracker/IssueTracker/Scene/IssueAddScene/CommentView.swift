//
//  CommentView.swift
//  IssueTracker
//
//  Created by juntaek.oh on 2022/06/21.
//

import UIKit

final class CommentView: UIView {

    let commentTextHolder = "코멘트는 여기에 작성해주세요"

    private var titleLabel: UILabel = {
        let label = UILabel()
        label.font = .smallRegular
        label.text = "제목"
        label.translatesAutoresizingMaskIntoConstraints = false

        return label
    }()

    private var titleTextField: UITextField = {
        let textField = UITextField()
        textField.font = .smallRegular
        textField.translatesAutoresizingMaskIntoConstraints = false

        return textField
    }()

    private(set) lazy var commentTextView: UITextView = {
        let textView = UITextView()
        textView.font = .smallRegular
        textView.textColor = .issueTrackerGray3
        textView.text = commentTextHolder
        textView.translatesAutoresizingMaskIntoConstraints = false

        return textView
    }()

    private let heightDividedLine = DividedLineView()
    private let widthDividedLine = DividedLineView()

    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .white
        setConstraints()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError()
    }
}

private extension CommentView {

    func setConstraints() {
        self.translatesAutoresizingMaskIntoConstraints = false
        self.addSubViews(titleLabel, titleTextField, commentTextView, heightDividedLine, widthDividedLine)

        NSLayoutConstraint.activate([
            titleLabel.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            titleLabel.topAnchor.constraint(equalTo: self.topAnchor, constant: 11),
            titleLabel.widthAnchor.constraint(equalToConstant: 30),
            titleLabel.heightAnchor.constraint(equalToConstant: 22),

            heightDividedLine.widthAnchor.constraint(equalToConstant: 2),
            heightDividedLine.leadingAnchor.constraint(equalTo: titleLabel.trailingAnchor, constant: 17),
            heightDividedLine.topAnchor.constraint(equalTo: self.topAnchor, constant: 11),
            heightDividedLine.heightAnchor.constraint(equalToConstant: 20),

            titleTextField.topAnchor.constraint(equalTo: self.topAnchor, constant: 11),
            titleTextField.heightAnchor.constraint(equalToConstant: 22),
            titleTextField.leadingAnchor.constraint(equalTo: heightDividedLine.trailingAnchor, constant: 10),
            titleTextField.trailingAnchor.constraint(equalTo: self.trailingAnchor),

            widthDividedLine.heightAnchor.constraint(equalToConstant: 1),
            widthDividedLine.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 20),
            widthDividedLine.trailingAnchor.constraint(equalTo: self.trailingAnchor),
            widthDividedLine.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 10),

            commentTextView.topAnchor.constraint(equalTo: widthDividedLine.bottomAnchor),
            commentTextView.bottomAnchor.constraint(equalTo: self.bottomAnchor),
            commentTextView.leadingAnchor.constraint(equalTo: self.leadingAnchor, constant: 16),
            commentTextView.trailingAnchor.constraint(equalTo: self.trailingAnchor)
        ])
    }
}
