//
//  ViewModelUnitTest.swift
//  ViewModelUnitTest
//
//  Created by 백상휘 on 2022/06/17.
//

import XCTest
@testable import IssueTracker15

class ViewModelUnitTest: XCTestCase {
    
    func testTestViewModel() {
        let expectation = XCTestExpectation()
        
        let vm = TestViewModel { _, _ in
            expectation.fulfill()
        }
        
        vm.request(TestBindableView(), param: nil)
        
        wait(for: [expectation], timeout: 7.0)
    }
}

class TestBindableView: UIView, ViewBindable {
    var vc: ViewBinding?
    
    func setVC(_ binding: ViewBinding) {
        
    }
    
    func sendAction(_ param: Any?) {
        
    }
    
    func receive(_ responseData: Any) {
        
    }
}
