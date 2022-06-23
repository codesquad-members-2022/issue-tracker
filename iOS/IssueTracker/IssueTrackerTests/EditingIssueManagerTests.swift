//
//  EditingIssueManagerTests.swift
//  IssueTrackerTests
//
//  Created by 김한솔 on 2022/06/23.
//

import XCTest
@testable import IssueTracker

class EditingIssueManagerTests: XCTestCase {

    var sut: EditingIssueManagable!

    override func setUpWithError() throws {
        sut = EditingIssueManager()
    }

    override func tearDownWithError() throws {
        sut = nil
    }

    func test_sendNewIssue호출시_key가id인데이터를_잘받아오는지() {
        let promise = expectation(description: "올바른 URL 생성")
        let url = URL(string: "https://008b1557-6228-4eb0-af91-8ea0225787e5.mock.pstmn.io")!
        let data = try! JSONEncoder().encode(["id": 1])
        let response = HTTPURLResponse(url: url, statusCode: 200, httpVersion: nil, headerFields: nil)

        let dummy = DummyData(data: data, response: response, error: nil)

        let stubURLSession = StubURLSession(dummy: dummy)

        sut.setURLSession(stubURLSession)

        let mockIssueItem = IssueItem(id: -1, title: "", content: "", milestoneName: "", labels: [])
        sut.sendNewIssue(mockIssueItem) { (result) in
            switch result {
            case .success(let idDictionary):
                let id = idDictionary["id"]
                XCTAssertNotNil(id)
                XCTAssertEqual(id!, 1)
                promise.fulfill()
            case .failure:
                return
            }
        }

        wait(for: [promise], timeout: 3)
    }
}
