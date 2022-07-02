//
//  UseCaseFactory.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/15.
//

import Foundation

struct UseCaseContainer: Resolvable {
    typealias Value = UseCaseResponsible
    typealias Creator = () -> Value
    
    static var shared: UseCaseContainer = UseCaseContainer()
    
    var map: [ObjectIdentifier: Creator] = [:]
    
    mutating func regist<T: UseCaseResponsible>(type: T.Type, make: @escaping Creator) {
        let identifier = ObjectIdentifier(type)
        map[identifier] = make
    }
    
    func resolve<T: UseCaseResponsible>(type: T.Type) -> Value? {
        let identifier = ObjectIdentifier(type)
        guard let useCaseCreator = map[identifier] else { return nil }
        return useCaseCreator()
    }

    private init() { }
    
}
    
