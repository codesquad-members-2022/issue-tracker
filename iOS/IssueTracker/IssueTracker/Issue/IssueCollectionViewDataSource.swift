//
//  IssueCollectionViewDataSource.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/15.
//

import UIKit

class IssueCollectionViewDataSource: NSObject, UICollectionViewDataSource {
    
    private var data: Observable<[IssueItem]>
    
    init(issueEntityList: Observable<[IssueItem]>) {
        self.data = issueEntityList
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        let issueEntityList = data.value
        return issueEntityList.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: IssueCollectionViewCell.identifier, for: indexPath) as? IssueCollectionViewCell else {
            return UICollectionViewCell()
        }
        let issueEntityList = data.value
        let issueEntity = issueEntityList[indexPath.item]
        cell.configure(with: issueEntity)
        
        return cell
    }
}
