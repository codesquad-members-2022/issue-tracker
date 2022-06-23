//
//  IssueListCell.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import UIKit

class IssueListCell: UICollectionViewCell,
                        ViewBindable {
    
    var indexPath: IndexPath?
    
    // MARK: - ViewBindable Implements. Select Multiple Issue List Functionality.
    
    var vc: ViewBinding?
    
    func sendAction(_ param: Any?) {
        vc?.inputViewEvent(self, param)
    }
    
    func receive(_ responseData: Any) {
        if let currentState = responseData as? Bool {
            print(currentState)
            self.checkSelectButton.isSelected = currentState
        }
    }
    
    func setVC(_ binding: ViewBinding) {
        vc = binding
    }
    
    var issueDTO: IssueDTO? {
        didSet {
            titleLabel.text = issueDTO?.title
            checkSelectButton.isSelected = issueDTO?.isSelected ?? false
            bodyLabel.text = issueDTO?.body
            mileStoneTitleLabel.text = "MileStoneMileStoneMileStoneMileStoneMileStoneMileStoneMileStoneMileStone"
            testLabels.text = "Documentation"
        }
    }
    
    // MARK: - UICollectionView Implements. Select Single Issue List Functionality.
    
    private var titleLabel: UILabel = {
        let label = UILabel()
        label.font = label.font.withSize(22)
        return label
    }()
    
    private lazy var checkSelectButton: CheckSelectButton = { // lazy for 'self'
        let button = CheckSelectButton()
        button.addTarget(self, action: #selector(checkSelectButtonTouchUpInsdie(_:)), for: .touchUpInside)
        return button
    }()
    
    private var bodyLabel: UILabel = {
        let label = UILabel()
        label.font = label.font.withSize(17)
        label.textColor = .gray
        label.numberOfLines = 2
        label.lineBreakMode = .byTruncatingTail
        return label
    }()
    
    private var mileStoneThumbnailLabel: UILabel = {
        let label = UILabel()
        label.text = "🪧"
        return label
    }()
    
    private var mileStoneTitleLabel: UILabel = {
        let label = UILabel()
        label.font = label.font.withSize(17)
        label.textColor = .gray
        label.adjustsFontSizeToFitWidth = true
        return label
    }()
    
    private var testLabels: UILabel = {
        let label = UILabel()
        return label
    }()
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        initializeUIElements()
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        initializeUIElements()
    }
    
    private func initializeUIElements() {
        contentView.addSubview(titleLabel)
        contentView.addSubview(checkSelectButton)
        contentView.addSubview(bodyLabel)
        contentView.addSubview(mileStoneThumbnailLabel)
        contentView.addSubview(mileStoneTitleLabel)
        contentView.addSubview(testLabels)
        
        titleLabel.snp.makeConstraints {
            $0.leading.equalToSuperview().offset(16)
            $0.top.equalToSuperview().offset(24)
            $0.height.equalTo(28)
        }
        checkSelectButton.snp.makeConstraints {
            $0.trailing.equalToSuperview().inset(16)
            $0.leading.equalTo(titleLabel.snp.trailing).offset(16)
            $0.top.equalTo(titleLabel)
            $0.width.equalTo(29)
            $0.height.equalTo(28)
        }
        bodyLabel.snp.makeConstraints {
            $0.leading.equalToSuperview().offset(16)
            $0.trailing.equalToSuperview().inset(16)
            $0.top.equalTo(titleLabel.snp.bottom).offset(16)
            $0.height.greaterThanOrEqualTo(22)
        }
        mileStoneThumbnailLabel.snp.makeConstraints {
            $0.leading.equalToSuperview().offset(16)
            $0.top.equalTo(bodyLabel.snp.bottom).offset(16)
            $0.width.equalTo(17)
            $0.height.equalTo(18)
        }
        mileStoneTitleLabel.snp.makeConstraints {
            $0.leading.equalTo(mileStoneThumbnailLabel.snp.trailing).offset(4)
            $0.top.height.equalTo(mileStoneThumbnailLabel)
            $0.trailing.equalToSuperview().inset(16)
        }
        testLabels.snp.makeConstraints {
            $0.leading.equalTo(mileStoneThumbnailLabel.snp.leading)
            $0.top.equalTo(mileStoneThumbnailLabel.snp.bottom).offset(16)
            $0.trailing.equalTo(mileStoneThumbnailLabel).inset(16)
            $0.bottom.greaterThanOrEqualToSuperview()
            $0.height.equalTo(16)
        }
        
        contentView.layoutIfNeeded()
    }
    
    @objc func checkSelectButtonTouchUpInsdie(_ sender: CheckSelectButton) {
        sendAction(nil)
    }
        
    // 공통으로 사용할 체크버튼인지 아직 알 수는 없기 때문에 Nested Type 같이 사용합니다.
    // MARK: - CheckButton inherited from UIButton
    class CheckSelectButton: UIButton {
        override var isSelected: Bool {
            didSet {
                setImage(isSelected ? .checkButtonImageFilled : .checkButtonImage, for: .normal)
                setNeedsDisplay()
            }
        }
    }
}
