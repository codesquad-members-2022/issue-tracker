//
//  ServiceContainer.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/16.
//

import Foundation

/// Service들을 Repository에서 DI 하고자 만든 클래스. 현재 사용하지 않음.
class ServiceContainer<S>: Resolvable {
    
    typealias Value = S
    
    private var services: [ObjectIdentifier: S] = [:]
    
    func regist<T>(instance: T) {
        let identifier = ObjectIdentifier(T.self)
        guard let service = instance as? S else { return } // is this type ServiceLayer??
        self.services[identifier] = service
    }
    
    func resolve<T>(type: T.Type) -> Value? {
        let identifier = ObjectIdentifier(type)
        guard let service = services[identifier] else { return nil }
        return service
    }
    
}
