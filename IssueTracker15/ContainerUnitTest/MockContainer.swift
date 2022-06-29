//
//  MockContainer.swift
//  ContainerUnitTest
//
//  Created by 박진섭 on 2022/06/15.
//

import Foundation

class MockContainer: Resolvable {
    typealias Value = MockProtocol
    typealias Creator = () -> Value
    
    var map: [ObjectIdentifier: Creator] = [:]
    
    func regist<T: Value>(type: T.Type, make: @escaping Creator) {
        let identifier = ObjectIdentifier(type)
        map[identifier] = make
    }
    
    func resolve<T: Value>(type: T.Type) -> Value? {
        let identifier = ObjectIdentifier(type)
        guard let useCase = map[identifier] else { return nil }
        return useCase()
    }    
}
