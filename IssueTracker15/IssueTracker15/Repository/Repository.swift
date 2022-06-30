//
//  Repository.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/29.
//

import Foundation

class Repository {
    static let shared = Repository()

    private var serviceWrapper = ContainerWrapper(container: ServiceContainer())
    
    let networkService = NetworkService<[IssueDTO]>()
    
    func getRESTNetworkService<T: Codable>(type: T.Type) -> NetworkService<T>? {
        
        guard let service = serviceWrapper.resolve(type: type.self) else {
            
            serviceWrapper.regist(type: type.self) {
                return NetworkService<T>()
            }
            
            let service = serviceWrapper.resolve(type: type.self)
            return service?() as? NetworkService<T>
        }
        
        return service() as? NetworkService<T>
    }
}
