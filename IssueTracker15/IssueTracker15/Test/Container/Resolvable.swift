//
//  Resolvable.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/15.
//

protocol Resolvable {
    associatedtype Value
    
    func regist<T>(instance: T)
    func resolve<T>(type: T.Type) -> Value?
}
