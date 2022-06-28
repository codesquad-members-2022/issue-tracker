//
//  Resolvable.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/15.
//

protocol Resolvable {
    associatedtype Value
    
    func regist<T>(type: T.Type, make: @escaping () -> Value)
    func resolve<T>(type: T.Type) -> (() -> Value)?
}

extension Resolvable where Value == UseCaseResponsible {
    func regist<T: UseCaseResponsible>(type: T.Type, make: @escaping () -> Value) { }
    
    func resolve<T: UseCaseResponsible>(type: T.Type) -> (() -> Value)? {
        return nil
    }
}
