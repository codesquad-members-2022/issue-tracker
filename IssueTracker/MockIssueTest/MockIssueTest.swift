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
                dump(issues)
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

    /// 체크하고 싶은 부분
    /// authorization code 를 받아오면 access token 을 요청하는지 체크
    /// 1. UserDefault 에 값을 없앤다.(앱초기화)
    /// 2. accessCode 를 받아오는 요청을 실행한다.
    /// 3. SceneDelegatge 에 응답이 오면 UserDefault에 토큰을 저장한다.
    /// 4. viewmodel에서 issueListRequest가 정상적으로 되는지 확인한다.
    ///
    /// 이거는 유닛테스틑 새로 파는게 좋겠는데? 아니면 다 한 테스트에 넣는게 좋으려나? 여기에 대한 명확한 기준이 필요하겠다
    func testSequiencialRequest() throws {

    }
}
