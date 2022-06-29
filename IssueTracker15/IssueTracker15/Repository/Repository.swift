//
//  Repository.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/29.
//

import Foundation

class Repository {
    static let shared = Repository()

    let serviceWrapper = ContainerWrapper(container: ServiceContainer())
    let networkService = NetworkService<IssueDTO>()
    let networkListService = NetworkService<[IssueDTO]>()
}
