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

    func test_didSelect호출시_설정된URL이_잘호출되는지() {
        var testString = ""

        sut.setOpenURLAction { url in
            testString = url.description
        }

        sut.didSelect()

        XCTAssertEqual(testString, "https://example.com")
    }

    func test_setOpenURLAction로_openURLAction설정시_잘설정되는지() {
        var testString2 = ""
        let confirmationString = "Test Completed"

        sut.setOpenURLAction({ _ in
            testString2 = confirmationString
        })

        sut.didSelect()

        XCTAssertEqual(testString2, confirmationString)
    }
}

struct MockSignInManager: SignInManagable {
    func requestCode(completion: @escaping (Result<URL, Error>) -> Void) {
        completion(.success(URL(string: "https://example.com")!))
    }

    func requestJWTToken(codeURL: URL, completion: @escaping (Result<[String: String], NetworkError>) -> Void) { }
}
