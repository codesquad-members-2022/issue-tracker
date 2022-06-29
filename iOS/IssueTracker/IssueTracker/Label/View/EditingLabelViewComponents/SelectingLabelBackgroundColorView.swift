//
//  SelectingLabelBackgroundColorView.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/29.
//

import UIKit

final class SelectingLabelBackgroundColorView: UIView {
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        label.numberOfLines = 1
        label.text = "배경색"
        return label
    }()

    let selectedBackgroundColorLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        label.font = UIFont(name: "SFProDisplay-Regular", size: 17)
        label.numberOfLines = 1
        return label
    }()

    let generateRandomColorButton: UIButton = {
        var configuration = UIButton.Configuration.plain()
        configuration.image = UIImage(systemName: "arrow.clockwise")
        configuration.baseForegroundColor = .black
        configuration.buttonSize = .small
        let button = UIButton(configuration: configuration)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        translatesAutoresizingMaskIntoConstraints = false
        setSubviewsLayout()
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

private extension SelectingLabelBackgroundColorView {
    func setSubviewsLayout() {
        let screenSize = UIScreen.main.bounds.size
        addSubviews(titleLabel, selectedBackgroundColorLabel, generateRandomColorButton)

        titleLabel.snp.makeConstraints { make in
            make.leading.equalToSuperview().offset(20/375 * screenSize.width)
            make.top.bottom.equalToSuperview()
        }

        selectedBackgroundColorLabel.snp.makeConstraints { make in
            make.leading.equalTo(titleLabel.snp.trailing).offset(61/375 * screenSize.width)
            make.top.bottom.equalToSuperview()
        }

        generateRandomColorButton.snp.makeConstraints { make in
            make.leading.equalTo(selectedBackgroundColorLabel).offset(137/375 * screenSize.width)
            make.top.bottom.equalToSuperview()
        }
    }
}
