//
//  Resolvable.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/15.
//

protocol Resolvable {
    associatedtype Value
    var map: [ObjectIdentifier: () -> Value] { get set }
}

extension Resolvable {
    mutating func regist<T>(type: T.Type, make: @escaping () -> Value) {
        let identifier = ObjectIdentifier(type)
        self.map[identifier] = make
    }
    
    func resolve<T>(type: T.Type) -> (() -> Value)? {
        let identifier = ObjectIdentifier(type)
        guard let useCase = map[identifier] else { return nil }
        return useCase
    }
}
