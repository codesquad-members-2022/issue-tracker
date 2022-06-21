//
//  PullListViewModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation

final class IssueListViewModel {
    private var mockDataService = MockDataService()
    
    var issueViewModels: Observable<[IssueTableCellViewModel]?> = Observable(nil)

    var numberOfViewModels: Int {
        return issueViewModels.value?.count ?? 0
    }
    
    func searchBarTextDidChange(with text: String) {
        // TODO: SearchBarText가 바뀌면 호출되는 부분 구현
    }
    
    func requestPullListData() {
        mockDataService.getMockIssues { issues in
            guard let issues = issues else {
                return
            }
            
            self.issueViewModels.value = convertModelToViewModel(issues)
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
}
