//
//  IssueCollectionView.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/14.
//

import UIKit

final class IssueCollectionView: UIView {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.backgroundColor = .white
        addSubviews(collectionView, addNewIssueButton)
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        fatalError()
    }
    
    private var collectionView: UICollectionView = {
        
        let flowLayout = UICollectionViewFlowLayout()
        flowLayout.minimumLineSpacing = 5
        flowLayout.estimatedItemSize = CGSize(width: 350, height: 240)
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: flowLayout)
        
        collectionView.isScrollEnabled = true
        collectionView.showsVerticalScrollIndicator = true
        collectionView.clipsToBounds = true
        collectionView.register(IssueCollectionViewCell.self, forCellWithReuseIdentifier: IssueCollectionViewCell.identifier)
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        return collectionView
    }()

    private let addNewIssueButton: UIButton = {
        let button = UIButton()
        button.translatesAutoresizingMaskIntoConstraints = false
        var configuration = UIButton.Configuration.filled()
        configuration.image = UIImage(systemName: "plus")
        configuration.baseBackgroundColor = .primary1
        configuration.cornerStyle = .capsule
        button.configuration = configuration
        return button
    }()
    
    func setDataSource(_ dataSource: UICollectionViewDataSource) {
        collectionView.dataSource = dataSource
    }

    func setCollectionViewDelegate(_ delegate: UICollectionViewDelegate) {
        collectionView.delegate = delegate
	}

    func setNewIssueButtonAction(_ action: UIAction) {
        addNewIssueButton.addAction(action, for: .touchUpInside)
    }
    
    override func layoutSubviews() {
        let screenSize = UIScreen.main.bounds.size

        collectionView.snp.makeConstraints { make in
            make.top.leading.trailing.bottom.equalTo(self)
        }

        addNewIssueButton.snp.makeConstraints { make in
            make.trailing.equalTo(safeAreaLayoutGuide.snp.trailing).offset(-20)
            make.bottom.equalTo(safeAreaLayoutGuide.snp.bottom).offset(-20)
            make.height.equalTo(64/375 * screenSize.width)
            make.width.equalTo(addNewIssueButton.snp.height)
        }
    }
}
