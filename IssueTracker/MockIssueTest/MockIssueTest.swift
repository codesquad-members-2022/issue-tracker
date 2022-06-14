//
//  MockIssueTest.swift
//  MockIssueTest
//
//  Created by YEONGJIN JANG on 2022/06/14.
//
import Foundation
import XCTest

@testable import IssueTracker

class MockIssueTest: XCTestCase {
    var manager: DecodeManager!

    func TestLocalMockIssues() {
        manager = DecodeManagerImpl()
        let issues = manager.getIssuesOnFailure()

        XCTAssert(issues?.count == 3, "failure at load local json file")
    }

}
