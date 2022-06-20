//
//  PullListViewModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation

final class PullListViewModel {
    private var mockDataService = MockDataService()
    private var pulls: [Issue]?
    
    var pullViewModelList: Observable<[PullTableCellViewModel]?> = Observable(nil)
    
    func searchBarTextDidChange(with text: String) {
        // TODO: SearchBarText가 바뀌면 호출되는 부분 구현
    }
    
    func requestPullListData() {
        mockDataService.getMockPullList { pullListModels in
            pulls = pullListModels
            
            guard let pulls = pulls else { return }
            
            convertPullsToPullCellViewModel(pulls)
        }
    }
    
    private func convertPullsToPullCellViewModel(_ list: [Issue]) {
        var tableCellViewModelLists = [PullTableCellViewModel]()
        for pull in list {
            let tableCellViewModel = PullTableCellViewModel()
            tableCellViewModel.configureCellData(with: pull)
            tableCellViewModelLists.append(tableCellViewModel)
        }
        self.pullViewModelList.value = tableCellViewModelLists
    }
}
