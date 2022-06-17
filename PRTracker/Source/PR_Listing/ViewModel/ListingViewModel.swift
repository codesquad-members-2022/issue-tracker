//
//  ListingViewModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation

final class ListingViewModel {
    private var mockDataService = MockDataService()
    var mockData: Observable<[ListingModel]?> = Observable(nil)
    
    let listTableViewCell: Observable<[PRTableCellViewModel]?> = Observable(nil)
    
    func searchBarTextDidChange(with text: String) {
        // TODO: SearchBarText가 바뀌면 호출되는 부분 구현
    }
    
    func requestData() {
        mockDataService.getMockPRList { model in
            mockData.value = [model]
        }
    }
}
