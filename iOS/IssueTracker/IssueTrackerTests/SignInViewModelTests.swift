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
    var testString = ""

    override func setUpWithError() throws {
        let openURL: (URL) -> Void = { (url) in
            self.testString = url.description
        }
        sut = SignInViewModel(useCase: MockSignInManager(),
                              actions: SignInViewModelActions(
                                openURL: openURL,
                                presentTabBarController: { }))
    }

    override func tearDownWithError() throws {
        sut = nil
    }

    func test_didSelect호출시_설정된URL이_잘호출되는지() {
        sut.didSelect()
        XCTAssertEqual(self.testString, "https://example.com")
    }

    func test_setPresentAction로_presentAction설정시_잘설정되는지() {
        var testString2 = ""
        let confirmationString = "Test Completed"

        sut.setPresentAction {
            testString2 = confirmationString
        }

        NotificationCenter.default.post(name: SceneDelegate.NotificationNames.didSignIn, object: self)

        XCTAssertEqual(testString2, confirmationString)
    }
}

struct MockSignInManager: SignInManagable {
    func requestCode(completion: @escaping (Result<URL, Error>) -> Void) {
        completion(.success(URL(string: "https://example.com")!))
    }

    func requestJWTToken(codeURL: URL, completion: @escaping (Result<[String: String], NetworkError>) -> Void) { }
}
