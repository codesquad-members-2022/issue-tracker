//
//  IssueManagerTests.swift
//  IssueTrackerTests
//
//  Created by Jason on 2022/07/01.
//

import XCTest
@testable import IssueTracker

final class IssueManagerTests: XCTestCase {
    
    private var issueManager: IssueManager!
    
    override func setUp() {
        super.setUp()
        
    }
    
    func text_should_be_able_to_load_to_issueItems() {

        // given
        let promise = expectation(description: "issueItem이 제대로 들어오는지")
        let url = URL(string: "https://008b1557-6228-4eb0-af91-8ea0225787e5.mock.pstmn.io/issues")!
        let data = try! JSONEncoder().encode(
            [IssueItem(id: 1, title: "", content: "", milestoneName: "", labels: [])])
        let response = HTTPURLResponse(url: url, statusCode: 200, httpVersion: nil, headerFields: nil)
        
        let dummyData = DummyData(data: data, response: response, error: nil)
        let stubURLSession = StubURLSession(dummy: dummyData)
        issueManager = IssueManager(urlSession: stubURLSession)
        
        // when
        issueManager.load { result in
            // then
            switch result {
            case .success(let data):
                XCTAssertTrue(data.count > 0)
                promise.fulfill()
            case .failure:
                return
            }
        }
        wait(for: [promise], timeout: 1)
    }
    
    override func tearDown() {
        super.tearDown()
        
    }
}
