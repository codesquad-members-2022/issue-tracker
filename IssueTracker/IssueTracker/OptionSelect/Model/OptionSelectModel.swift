//
//  OptionSelectModel.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/30.
//

import Foundation

class OptionSelectModel {
    
    private let service: IssueService
    private var options: [Optionable] {
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
    
    func getOption(index: Int) -> Optionable {
        return options[index]
    }
        
    func requestOptions(_ option: Option, repo: Repository) {
        switch option {
        case .label:
            service.requestRepositoryLabels(repo: repo) { [weak self] result in
                switch result {
                case .success(let repositoryList):
                    self?.options = repositoryList
                case .failure(let error):
                    print(error)
                }
            }
        case .milestone:
            service.requestRepositoryMilestones(repo: repo) { [weak self] result in
                switch result {
                case .success(let repositoryList):
                    self?.options = repositoryList
                case .failure(let error):
                    print(error)
                }
            }
        case .assignee:
            service.requestRepositoryAssigness(repo: repo) { [weak self] result in
                switch result {
                case .success(let repositoryList):
                    self?.options = repositoryList
                case .failure(let error):
                    print(error)
                }
            }
        }
        
    }
}
