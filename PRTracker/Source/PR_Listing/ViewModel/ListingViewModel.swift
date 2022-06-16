//
//  ListingViewModel.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/13.
//

import Foundation

final class ListingViewModel {
    let listTableViewCell: Observable<[PRTableCellViewModel]?> = Observable(nil)
    let searchBarText: Observable<String?> = Observable(nil)
    
    func searchBarTextDidChange(with text: String) {
        self.searchBarText.value = text
    }
}
