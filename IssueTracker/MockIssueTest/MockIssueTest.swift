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
        issueRepository = IssueTrackingRepository()
        let issues = issueRepository.getIssuesOnFailure()

        XCTAssertNotNil(issues, "Issue is NIL")
    }

    func testRequestIssueList() throws {
        guard let token = UserDefaults.standard.object(forKey: Environment.token) as? String else { return }
        issueRepository = IssueTrackingRepository()
        let expectation = expectation(description: "requestIssueList")
        var container: [Issue]?
        issueRepository.requestIssues(with: .requestIssueList(token: token)) { response in
            switch response {
            case .success(let issues):
                container = issues
            case .failure(let error):
                dump(error.localizedDescription)
            }
            expectation.fulfill()
        }
        wait(for: [expectation], timeout: 5.0)
        XCTAssertNotNil(container, "값이 여기에 안담겼달까..?")
    }

}
