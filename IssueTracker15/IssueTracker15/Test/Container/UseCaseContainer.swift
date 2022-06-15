//
//  UseCaseFactory.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/15.
//

import Foundation



class UseCaseContainer: Resolvable {
    typealias Value = UseCaseResponsible
    
    static let shard: UseCaseContainer = UseCaseContainer()
    
    private var useCases: [ObjectIdentifier: UseCaseResponsible] = [:]
    
    func regist<T>(instance: T) {
        let identifier = ObjectIdentifier(T.self)
        guard let useCase = instance as? UseCaseResponsible else { return }
        self.useCases[identifier] = useCase
    }
    
    func resolve<T>(type: T.Type) -> Value? {
        let identifier = ObjectIdentifier(type)
        guard let useCase = useCases[identifier] else { return nil }
        return useCase
    }

    private init() { }
    
}
    
