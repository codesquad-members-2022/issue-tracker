//
//  PullListViewModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation

final class PullListViewModel {
    private var mockDataService = MockDataService()
    
    var pullViewModelList: Observable<[PullTableCellViewModel]?> = Observable(nil)

    var numberOfViewModels: Int {
        return pullViewModelList.value?.count ?? 0
    }
    
    func searchBarTextDidChange(with text: String) {
        // TODO: SearchBarText가 바뀌면 호출되는 부분 구현
    }
    
    func requestPullListData() {
        mockDataService.getMockPullList { pullListModels in
            guard let pulls = pullListModels else { return }
            self.pullViewModelList.value = convertModelToViewModel(pulls)
        }
    }
    
    private func convertModelToViewModel(_ list: [Pull]) -> [PullTableCellViewModel] {
        let tableCellViewModelList = list.map { pull -> PullTableCellViewModel in
            let tableCellViewModel = PullTableCellViewModel()
            tableCellViewModel.configureCellData(with: pull)
            return tableCellViewModel
        }
        return tableCellViewModelList
    }
    
    func getCellViewModel(index: Int) -> PullTableCellViewModel? {
        return pullViewModelList.value?[index]
    }
}
