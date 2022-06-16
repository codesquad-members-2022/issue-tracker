//
//  IssueCollectionViewDataSource.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/15.
//

import UIKit

class IssueCollectionViewDataSource: NSObject, UICollectionViewDataSource {
    
    private var issueData: [IssueItem] = [.init(title: "제목", issueDescription: "이슈에 대한 설명(최대 두 줄까지 보여줄 수 있다)", milestoneName: "마일스톤 이름") ]
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        issueData.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: IssueCollectionViewCell.identifier, for: indexPath) as? IssueCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        if kind == UICollectionView.elementKindSectionHeader {
            let header = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: IssueCollectionHeaderView.identifier, for: indexPath)
            return header
        } else { return UICollectionReusableView() }
    }
}
