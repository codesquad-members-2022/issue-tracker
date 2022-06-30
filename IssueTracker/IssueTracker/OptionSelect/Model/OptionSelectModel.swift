//
//  OptionSelectModel.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/30.
//

import Foundation

class OptionSelectModel {
    
    private let service: IssueService
    private var options: [Repository] {
        didSet {
            updatedOptions?()
        }
    }
    
    var updatedOptions: (() -> Void)?
    
    init(service: IssueService) {
        self.service = service
        self.options = []
    }
    
    func getOptionsCount() -> Int {
        return options.count
    }
    
    func getOption(index: Int) -> Repository {
        return options[index]
    }
        
    func requestRepos() {
        service.requestRepos() { [weak self] result in
            switch result {
            case .success(let repositoryList):
                self?.options = repositoryList
            case .failure(let error):
                print(error)
            }
        }
    }
}
