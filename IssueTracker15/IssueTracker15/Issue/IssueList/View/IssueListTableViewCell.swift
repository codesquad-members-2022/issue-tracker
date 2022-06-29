//
//  IssueListTableViewCell.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/27.
//

import UIKit
import SnapKit

class IssueListTableViewCell: UITableViewCell, ViewBindable {
    
    var willBeDelete = false
    var vc: ViewBinding?
    var indexPath: IndexPath?
    var dto: IssueDTO? {
        didSet {
            titleLabel.text = dto?.title
            titleLabel.textColor = (dto?.closed_at?.isEmpty ?? true) ? .label : .purple
            checkSelectButton.isSelected = dto?.isSelected ?? false
            bodyLabel.text = dto?.body
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

    override func awakeFromNib() {
        super.awakeFromNib()
        setUI()
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setUI()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUI()
    }
    
    private func setUI() {
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
    
    func sendAction(_ param: Any?) {
        vc?.inputViewEvent(self, param)
    }
    
    func receive(_ responseData: Any) {
        guard let currentState = responseData as? Bool else {
            return
        }
        
        self.checkSelectButton.isSelected = currentState
    }
    
    func setVC(_ binding: ViewBinding) {
        self.vc = binding
    }
    
    func showButton(_ isShow: Bool) {
        checkSelectButton.isHidden = !isShow
    }
}

class CheckSelectButton: UIButton {
    override var isSelected: Bool {
        didSet {
            setImage(isSelected ? .checkButtonImageFilled : .checkButtonImage, for: .normal)
            setNeedsDisplay()
        }
    }
}
