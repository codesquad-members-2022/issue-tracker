//
//  EditingIssueTitleViewComponents.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/23.
//

import SnapKit

final class EditingIssueTitleViewComponents: UIStackView {
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.text = "제목"
        label.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        return label
    }()

    let titleTextField: UITextField = {
        let textField = UITextField()
        let screenSize = UIScreen.main.bounds.size
        textField.translatesAutoresizingMaskIntoConstraints = false
        textField.font = UIFont(name: "SFProDisplay-Regular", size: 17.0/812.0 * screenSize.height)
        textField.alpha = 1
        textField.autocorrectionType = .no
        textField.autocapitalizationType = .none
        return textField
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        axis = .horizontal
        distribution = .fillProportionally
        addArrangedSubviews(titleLabel, titleTextField)
        setTitleTextField()
    }

    @available(*, unavailable)
    required init(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        let screenSize = UIScreen.main.bounds.size

        titleLabel.snp.makeConstraints { make in
            make.width.equalTo(64/375 * screenSize.width)
        }
    }
}

private extension EditingIssueTitleViewComponents {
    func setTitleTextField() {
        let action = UIAction { [weak self] _ in
            guard let self = self else { return }
            self.titleTextField.delegate?.textFieldDidBeginEditing?(self.titleTextField)
        }

        titleTextField.addAction(action, for: .editingChanged)
    }
}
