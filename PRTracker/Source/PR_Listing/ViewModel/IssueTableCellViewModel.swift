//
//  IssueTableCellViewModel.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/15.
//

import Foundation

final class IssueTableCellViewModel {
    let title: Observable<String?> = Observable(nil)
    let content: Observable<String?> = Observable(nil)
    let projectName: Observable<String?> = Observable(nil)
    let labelList: Observable<[LabelViewModel]?> = Observable(nil)
    let commentCount: Observable<Int?> = Observable(nil)
    
    func configureCellData(with data: Issue) {
        // TODO: PRTableCellViewModel 구성하기 (지금은 임시로 title, body, mileStone의 title만 표시하도록 함)
        self.title.value = data.title
        self.content.value = data.body
        self.projectName.value = data.milestone?.title
    }
}
