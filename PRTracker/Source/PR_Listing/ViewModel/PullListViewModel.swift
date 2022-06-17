//
//  PullListViewModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation

final class PullListViewModel {
    private var mockDataService = MockDataService()
    var mockData: Observable<[PullListModel]?> = Observable(nil)
    
    private var tableCellViewModelLists = [PullTableCellViewModel]()
    private let tableCellViewModel = PullTableCellViewModel()
    
    func searchBarTextDidChange(with text: String) {
        // TODO: SearchBarText가 바뀌면 호출되는 부분 구현
    }
    
    func requestData() {
        mockDataService.getMockPRList { listingModelList in
            mockData.value = listingModelList
            guard let _ = mockData.value?.count,
            let mockDatas = mockData.value else { return }
            
            // TODO: 테이블뷰셀 구성하기
            // ListingViewModel이 전체 데이터를 받아오고 있으므로,
            // 받아온 전체 데이터를 PRTableCellViewModel에 전달하고, PRTableCellViewModel은
            // 그걸 데이터를 바탕으로 자신을 구성한다.
            // 그리고 구성된 PRTableCellViewModel을 이용하여 테이블뷰셀을 구성하고 표시.
            configureTableCellViewData(with: mockDatas)
        }
    }
    
    private func configureTableCellViewData(with modelList: [PullListModel]) {
        for model in modelList {
            tableCellViewModel.configureCellData(with: model)
            tableCellViewModelLists.append(tableCellViewModel)
        }
    }
}
