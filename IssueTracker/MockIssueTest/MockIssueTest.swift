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

    func testLocalMockIssues() throws {
        manager = DecodeManagerImpl()
        let issues = manager.getIssuesOnFailure()

        XCTAssertEqual(issues?.count, 3)
    }

}
