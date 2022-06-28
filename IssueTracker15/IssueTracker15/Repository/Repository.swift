//
//  Repository.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/15.
//

import Foundation
import Alamofire

/// 모델들이 Service와 소통하기 위한 Repository 클래스. 현재 사용하지 않음.
//class Repository {
//    static let shared = Repository()
//    
//    let serviceWrapper = ContainerWrapper(container: ServiceContainer<Any>())
//    
//    func getNetworkService<T: Codable>(resultType: T.Type) -> NetworkService<T>? {
//        let service = NetworkService<T>()
//        
//        serviceWrapper.regist(instance: service)
//        return serviceWrapper.resolve(type: NetworkService<T>.self) as? NetworkService<T>
//    }
//}
//
//enum ServiceType {
//    case network(URLRequest, RequestUrgency)
//    case persistent
//}
