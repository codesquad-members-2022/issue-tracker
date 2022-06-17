//
//  PullTableCellViewModel.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/15.
//

import Foundation

final class PullTableCellViewModel {
    let title: Observable<String?> = Observable(nil)
    let content: Observable<String?> = Observable(nil)
    let projectName: Observable<String?> = Observable(nil)
    let labelList: Observable<[LabelViewModel]?> = Observable(nil)
    let commentCount: Observable<Int?> = Observable(nil)
    let cellData: Observable<PullListModel?> = Observable(nil)
    
    func configureCellData(with data: PullListModel) {
        // TODO: PRTableCellViewModel 구성하기
    }
}
