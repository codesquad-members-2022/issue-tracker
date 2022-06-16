//
//  ContainerUnitTest.swift
//  ContainerUnitTest
//
//  Created by 박진섭 on 2022/06/15.
//

import XCTest

class ContainerUnitTest: XCTestCase {
    
    var sut: ContainerWrapper<MockContainer>!
    
    func testRegist() {
        let mockContainer = MockContainer()
        sut = ContainerWrapper(container: mockContainer)
        
        // value
        let mockValue = MockValue()
        // key
        let mockValueType = ObjectIdentifier(MockValue.self)
        
        sut.regist(instance: mockValue)
        
        guard let registedValue = mockContainer.map[mockValueType] as? MockValue else {
            XCTFail()
            return
        }
        
        XCTAssertEqual(mockValue, registedValue)
    }
    
    func testResolve() {
        let mockContainer = MockContainer()
        sut = ContainerWrapper(container: mockContainer)
        
        // value
        let mockValue = MockValue()
        sut.regist(instance: mockValue)
        
        guard let resolvedValue = sut.resolve(type: MockValue.self) as? MockValue else {
            XCTFail()
            return
        }
        
        XCTAssertEqual(mockValue, resolvedValue)
    }
}
