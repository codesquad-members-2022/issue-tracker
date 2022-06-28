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

    func test_didTouchGitHubSignIn호출시_설정된URL이_잘호출되는지() {
        var testString = ""

        sut.gitHubSignInURL.bind(on: self) { url in
            guard let url = url else { return }
            testString = url.description
        }

        sut.didTouchGitHubSignIn()

        XCTAssertEqual(testString, "https://example.com")
    }
}

struct MockSignInManager: SignInManagable {
    func requestCode(completion: @escaping (Result<URL, Error>) -> Void) {
        completion(.success(URL(string: "https://example.com")!))
    }

    func requestJWTToken(codeURL: URL, completion: @escaping (Result<StringDictionary, NetworkError>) -> Void) { }

    mutating func setURLSession(_ session: URLSessionProtocol) { }
}
