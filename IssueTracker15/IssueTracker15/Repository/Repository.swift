//
//  Repository.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/15.
//

import Foundation
import Alamofire

/// 모델들이 Service와 소통하기 위한 Repository 클래스. 현재 사용하지 않음.
class Repository {
    static let shared = Repository()
    
    var networkService = NetworkService<TestDecodableType>()
}

enum ServiceType {
    case network(URLRequest, RequestUrgency)
    case persistent
}
