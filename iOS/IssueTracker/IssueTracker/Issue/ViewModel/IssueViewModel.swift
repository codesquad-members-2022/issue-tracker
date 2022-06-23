//
//  IssueViewModel.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/23.
//

import Foundation

struct IssueViewModel {
    let issue: IssueItem
}

class IssueListViewModel {
    
    var issueListViewModel: [IssueViewModel]
    
    init() {
        self.issueListViewModel = [issueListViewModel]()
    }
    
    func issueListViewModel(at index: Int) -> IssueViewModel {
        return self.issueListViewModel[index]
    }
    
}

extension IssueViewModel {
    
    var id: Int {
        return self.issue.id
    }
    
    var title: String {
        return self.issue.title
    }
    
    var content: String {
        return self.issue.content
    }
    
    var milestoneName: String {
        return self.issue.milestoneName
    }
    
    var labels: [Label] {
        return self.issue.labels
    }
}
