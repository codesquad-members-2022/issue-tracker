//
//  IssueCollectionViewDataSource.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/15.
//

import UIKit

class IssueCollectionViewDataSource: NSObject, UICollectionViewDataSource {
    
    var data: [IssueItem] = [
        .init(id: 1, title: "제목", content: "이슈에 대한 설명", milestoneName: "마일스톤 이름", labels: [
            .init(title: "레이블 이름", backgroundColor: "#677ae5")
        ]),
        .init(id: 2, title: "제목", content: "이슈에 대한 설명", milestoneName: "마일스톤 이름", labels: [
            .init(title: "Bug", backgroundColor: "#20bb87"),
            .init(title: "iOS", backgroundColor: "#20bb87")
        ])
    ]
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        data.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: IssueCollectionViewCell.identifier, for: indexPath) as? IssueCollectionViewCell else {
            return UICollectionViewCell()
        }
        
        cell.configure(title: data[indexPath.item].title,
                       issueDescription: data[indexPath.item].content,
                       milestoneName: data[indexPath.item].milestoneName,
                       labelName: data[indexPath.item].labels[0].title,
                       labelBackgroundColor: data[indexPath.item].labels[0].backgroundColor)
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        if kind == UICollectionView.elementKindSectionHeader {
            guard let header = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: IssueCollectionHeaderView.identifier, for: indexPath) as? IssueCollectionHeaderView else {
                return UICollectionReusableView()
            }
            header.setHeaderTitle(text: "이슈")
            return header
        } else {
            return UICollectionReusableView()
        }
    }
}
