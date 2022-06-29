//
//  IssueTableCellViewModel.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/15.
//

import Foundation

final class IssueTableCellViewModel {
    var id: Int = 0
    var title: String = ""
    var state: String = ""
    var content: String = ""
    var projectName: String = ""
    var labelList = [Label]()
    
    func configureCellData(with data: Issue) {
        self.id = data.id
        self.title = data.title
        self.content = data.body ?? "No content"
        self.projectName = data.milestone?.title ?? "No Milestone"
        self.labelList = data.labels
        self.state = data.state
    }
}
