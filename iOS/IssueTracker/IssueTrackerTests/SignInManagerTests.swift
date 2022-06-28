//
//  SignInManagerTests.swift
//  IssueTrackerTests
//
//  Created by 김한솔 on 2022/06/20.
//

import XCTest
@testable import IssueTracker

class SignInManagerTests: XCTestCase {

    var sut: SignInManager!

    func test_requestCode호출시_올바른URL을_생성하는지() {
        let promise = expectation(description: "올바른 URL 생성")
        let url = URL(string: "https://github.com/login/oauth/authorize")!
        let data = "a1b2c3d4".data(using: .utf8)
        let response = HTTPURLResponse(url: url, statusCode: 200, httpVersion: nil, headerFields: nil)

        let dummy = DummyData(data: data, response: response, error: nil)

        let stubURLSession = StubURLSession(dummy: dummy)

        sut = SignInManager(urlSession: stubURLSession)

        sut.requestCode { result in
            switch result {
            case .success(let url):
                XCTAssertNotNil(url.description.split(separator: "=").last)
                promise.fulfill()
            case .failure:
                return
            }
        }

        wait(for: [promise], timeout: 10)
    }

    func test_requestJWTToken호출시_data에담긴_JWTToken이_제대로반환되는지() {
        let promise = expectation(description: "data담긴 JWTToken이 반환")
        let url = URL(string: "https://008b1557-6228-4eb0-af91-8ea0225787e5.mock.pstmn.io/login/oauth/github")!
        let dictionary = ["JWT_access_token": "a1b2c3d4"]
        let data = try! JSONEncoder().encode(dictionary)
        let response = HTTPURLResponse(url: url, statusCode: 200, httpVersion: nil, headerFields: nil)

        let dummy = DummyData(data: data, response: response, error: nil)

        let stubURLSession = StubURLSession(dummy: dummy)

        sut = SignInManager(urlSession: stubURLSession)

        let codeURL = URL(string: "issuetrackerapp://?code=a1b2c3d4")!
        sut.requestJWTToken(codeURL: codeURL) { result in
            switch result {
            case let .success(jwtToken):
                XCTAssertNotNil(jwtToken["JWT_access_token"])
                promise.fulfill()
            case .failure:
                return
            }
        }

        wait(for: [promise], timeout: 3)
    }
}
