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
        manager = DecodeManagerImplement()
        let issues = manager.getIssuesOnFailure()

        XCTAssertNotNil(issues, "Issue is NIL")
    }

}
