//
//  ServiceContainer.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/29.
//

import Foundation

struct ServiceContainer: Resolvable {
    typealias Value = CommonService
    typealias Creator = () -> Value
    
    var map: [ObjectIdentifier: Creator] = [:]
    
    mutating func regist<T: Value>(type: T.Type, make: @escaping Creator) {
        let identifier = ObjectIdentifier(type)
        map[identifier] = make
    }
    
    func resolve<T: Value>(type: T.Type) -> Value? {
        let identifier = ObjectIdentifier(type)
        guard let service = map[identifier] else { return nil }
        return service()
    }
}
