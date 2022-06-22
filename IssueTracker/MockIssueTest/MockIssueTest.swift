//
//  MockIssueTest.swift
//  MockIssueTest
//
//  Created by YEONGJIN JANG on 2022/06/14.
//
import Foundation
import XCTest

@testable import IssueTracker

final class MockIssueTest: XCTestCase {

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

    func testHasQueryIdAndScope() throws {
        // when
        let authrizeRequestTarget = IssueTrackerTarget.requestAuthorizeCode

        // given
        guard let request = Provider
                .makeURLRequest(with: authrizeRequestTarget),
              let query = request.url?.query else { return }

        // then
        XCTAssertTrue(query.contains("client_id"), "query doesn't have CLIENT_ID, \(query)")
        XCTAssertTrue(query.contains("scope"), "query doesn't have SCOPE, \(query)")
    }

}
