//
//  PullListViewModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation

final class IssueListViewModel {
    private let issueService: IssueService
    
    var issueViewModels: Observable<[IssueTableCellViewModel]?> = Observable(nil)

    var numberOfViewModels: Int {
        return issueViewModels.value?.count ?? 0
    }
    
    init(issueService: IssueService = IssueManager()) {
        self.issueService = issueService
    }
    
    func searchBarTextDidChange(with text: String) {
        // TODO: SearchBarText가 바뀌면 호출되는 부분 구현
    }
    
    func requestData() {
        issueService.getIssues { issues in
            guard let issues = issues else {
                return
            }
            
            self.issueViewModels.value = self.convertModelToViewModel(issues)
        }
    }
    
    private func convertModelToViewModel(_ list: [Issue]) -> [IssueTableCellViewModel] {
        let tableCellViewModelList = list.map { pull -> IssueTableCellViewModel in
            let tableCellViewModel = IssueTableCellViewModel()
            tableCellViewModel.configureCellData(with: pull)
            return tableCellViewModel
        }
        return tableCellViewModelList
    }
    
    func getCellViewModel(index: Int) -> IssueTableCellViewModel? {
        return issueViewModels.value?[index]
    }
    
    func close(at index: Int, completion: @escaping (Bool) -> Void) {
        issueService.closeIssue(at: index) {
            self.issueViewModels.value?.remove(at: index)
            completion(true)
        }
    }
}
