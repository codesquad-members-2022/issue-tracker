//
//  TitleView.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/24.
//

import UIKit

final class TitleView: UIView {
    private let leftButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: "line.3.horizontal.decrease.circle"), for: .normal)
        button.tintColor = .primary
        return button
    }()

    private let rightButton: UIButton = {
        let button = UIButton()
        button.setImage(UIImage(systemName: "checkmark.circle"), for: .normal)
        button.tintColor = .primary
        return button
    }()

    private let titleLabel: UILabel = {
        let label = UILabel()
        label.text = "이슈"
        label.font = .systemFont(ofSize: 38, weight: .bold)
        return label
    }()

    private let line: UIView = {
        let view = UIView()
        view.backgroundColor = .line
        return view
    }()

    override init(frame: CGRect) {
        super.init(frame: frame)
        backgroundColor = .white
        layout()
    }

    @available(*, unavailable)
    required init?(coder _: NSCoder) {
        fatalError("Init with coder is unavailable")
    }

    private func layout() {
        addSubview(leftButton)
        addSubview(rightButton)
        addSubview(titleLabel)
        addSubview(line)

        leftButton.snp.makeConstraints {
            $0.top.leading.equalToSuperview().offset(30)
        }

        rightButton.snp.makeConstraints {
            $0.top.equalToSuperview().offset(30)
            $0.trailing.equalToSuperview().offset(-30)
        }

        titleLabel.snp.makeConstraints {
            $0.top.equalTo(leftButton.snp.bottom).offset(20)
            $0.leading.equalToSuperview().offset(30)
        }

        line.snp.makeConstraints {
            $0.top.equalTo(titleLabel.snp.bottom).offset(20)
            $0.leading.equalToSuperview().offset(30)
            $0.trailing.equalToSuperview().offset(-30)
            $0.height.equalTo(1)
        }
    }
}
