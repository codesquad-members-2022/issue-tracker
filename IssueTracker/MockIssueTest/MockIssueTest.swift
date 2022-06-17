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

    var issueRepository: IssueTrackingRepository!

    func testLocalMockIssues() throws {
//        manager = DecodeManagerImplement()
        issueRepository = IssueTrackingRepository()
        let issues = issueRepository.getIssuesOnFailure()

        XCTAssertNotNil(issues, "Issue is NIL")
    }

}
