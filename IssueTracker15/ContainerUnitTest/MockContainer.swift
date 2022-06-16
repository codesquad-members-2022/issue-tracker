//
//  MockContainer.swift
//  ContainerUnitTest
//
//  Created by 박진섭 on 2022/06/15.
//

import Foundation

class MockContainer: Resolvable {
    typealias Value = MockProtocol
    
    var map: [ObjectIdentifier: MockProtocol] = [:]
    
    func regist<T>(instance: T) {
        let identifier = ObjectIdentifier(T.self)
        guard let useCase = instance as? Value else { return }
        self.map[identifier] = useCase
    }
    
    func resolve<T>(type: T.Type) -> Value? {
        let identifier = ObjectIdentifier(type)
        guard let useCase = map[identifier] else { return nil }
        return useCase
    }    
}
