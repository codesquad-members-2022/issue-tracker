//
//  IssueCollectionViewDataSource.swift
//  IssueTracker
//
//  Created by 이건행 on 2022/06/15.
//

import UIKit

class IssueCollectionViewDataSource: NSObject, UICollectionViewDataSource {
    
//    var data: [IssueItem] = [
//        .init(id: 1, title: "제목", content: "이슈에 대한 설명", milestoneName: "마일스톤 이름", labels: [
//            .init(title: "레이블 이름", backgroundColor: "#677ae5")
//        ]),
//        .init(id: 2, title: "제목", content: "이슈에 대한 설명", milestoneName: "마일스톤 이름", labels: [
//            .init(title: "Bug", backgroundColor: "#20bb87"),
//            .init(title: "iOS", backgroundColor: "#20bb87")
//        ]),
//        .init(id: 3, title: "제목", content: "이슈에 대한 설명", milestoneName: "마일스톤 이름", labels: [
//            .init(title: "Bug", backgroundColor: "#20bb87"),
//            .init(title: "iOS", backgroundColor: "#20bb87")
//        ])
//    ]
    var issueListViewModel = IssueListViewModel()
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
//        data.count
        return self.issueListViewModel.allIssueViewModel.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: IssueCollectionViewCell.identifier, for: indexPath) as? IssueCollectionViewCell else {
            return UICollectionViewCell()
        }
//        let issueEntity = data[indexPath.item]
//        cell.configure(with: issueEntity)
        
        return cell
    }
}
