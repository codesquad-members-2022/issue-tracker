//
//  IssueListViewModel.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import Foundation

class IssueListViewModel: CommonViewModel {
    
    private(set) var issueList = Array(repeating: IssueDTO.empty, count: 25)
    
    var output: (Any?, ViewBindable) -> Void

    init(_ output: @escaping (Any?, ViewBindable) -> Void) {
        self.output = output
        
        for i in issueList.indices {
            issueList[i].id = i
        }

    }

    func request(_ bindable: ViewBindable, param: Any?) { }
    
    func getIssues() { }
    
    @discardableResult
    func selectList(_ cell: IssueListTableViewCell) -> Bool {
        
        guard
            let issueId = cell.dto?.id,
            let inx = self.issueList.firstIndex(where: { $0.id == issueId })
        else {
            return false
        }
        
        issueList[inx].isSelected.toggle()
        output(nil, cell)
        return true
    }
}
