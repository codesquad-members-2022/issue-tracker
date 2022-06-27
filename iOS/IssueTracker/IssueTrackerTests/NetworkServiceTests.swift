//
//  NetworkServiceTests.swift
//  IssueTrackerTests
//
//  Created by 김한솔 on 2022/06/20.
//

import XCTest
@testable import IssueTracker

class NetworkServiceTests: XCTestCase {

    func test_fetchData호출시_URLSession에서넘어온data를_잘반환하는지() {
        let promise = expectation(description: "test")
        let url = URL(string: "https://example.com")!
        let data = try! JSONEncoder().encode("TestString")
        let response = HTTPURLResponse(url: url, statusCode: 200, httpVersion: nil, headerFields: nil)

        let dummy = DummyData(data: data, response: response, error: nil)

        let stubURLSession = StubURLSession(dummy: dummy)

        NetworkService<String>.fetchData(target: TestNetworkTarget.testCase, urlSession: stubURLSession) { result in
            switch result {
            case .success(let dataString):
                XCTAssertEqual(dataString, "TestString")
                promise.fulfill()
            case .failure:
                return
            }
        }

        wait(for: [promise], timeout: 3)
    }
}

private extension NetworkServiceTests {
    enum TestNetworkTarget: NetworkTargetProtocol {
        case testCase

        var url: String {
            return "https://example.com"
        }

        var queryItem: [URLQueryItem]? {
            return nil
        }

        var method: String? {
            return nil
        }

        var body: Data? {
            return nil
        }
    }
}
