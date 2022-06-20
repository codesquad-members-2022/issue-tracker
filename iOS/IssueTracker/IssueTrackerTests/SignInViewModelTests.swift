//
//  SignInViewModelTests.swift
//  IssueTrackerTests
//
//  Created by 김한솔 on 2022/06/20.
//

import XCTest
@testable import IssueTracker

class SignInViewModelTests: XCTestCase {

    var sut: SignInViewModel!

    override func setUpWithError() throws {
        sut = SignInViewModel(useCase: MockSignInManager())
    }

    override func tearDownWithError() throws {
        sut = nil
    }

    func test_requestOAuthCode호출시_buttonAction이_잘호출되는지() {
        var checkString = ""
        sut.buttonAction.bind(on: self) { url in
            checkString = "buttonAction called"
        }

        sut.requestOAuthCode()

        XCTAssertEqual(checkString, "buttonAction called")
    }
}

struct MockSignInManager: SignInManagable {
    func requestCode(completion: @escaping (Result<URL, Error>) -> Void) {
        completion(.success(URL(string: "https://example.com")!))
    }

    func requestJWTToken(codeURL: URL, completion: @escaping (Result<[String: String], NetworkError>) -> Void) { }
}
