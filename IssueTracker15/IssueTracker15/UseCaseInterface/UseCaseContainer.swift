//
//  UseCaseFactory.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/15.
//

import Foundation

typealias Creator = () -> UseCaseResponsible

final class UseCaseContainer: Resolvable {
    typealias Value = UseCaseResponsible
    
    static let shared: UseCaseContainer = UseCaseContainer()
    
    private var useCases: [ObjectIdentifier: Creator] = [:]
    
    func regist<T>(type: T.Type, make: @escaping Creator) {
        let identifier = ObjectIdentifier(type)
        useCases[identifier] = make
    }
    
    func resolve<T>(type: T.Type) -> Creator? {
        let identifier = ObjectIdentifier(type)
        guard let useCase = useCases[identifier] else { return nil }
        return useCase
    }

    private init() { }
    
}
    
