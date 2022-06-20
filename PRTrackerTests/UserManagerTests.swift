//
//  UserManagerTests.swift
//  PRTrackerTests
//
//  Created by Bumgeun Song on 2022/06/20.
//

import XCTest


class UserManagerTests: XCTestCase {

    var sut: UserManager!
    
    override func setUpWithError() throws {
        super.setUp()
    }
    
    func test_UserManager_should_return_User_when_token_is_stored() throws {
        // Given - keyChain에 token이 저장돼 있을 때
        let expectation = XCTestExpectation(description: "User info returned")
        sut = UserManager(keyChainService: KeyChainSuccessStub())
        
        // When - User 정보를 요청하면
        // Then - 토큰에 해당하는 User 정보를 return한다.
        sut.getCurrentUser { user in
            XCTAssertEqual(user?.id, 17468015)
            XCTAssertEqual(user?.userName, "BumgeunSong")
            XCTAssertEqual(user?.reposURL, "https://api.github.com/users/BumgeunSong/repos")
            expectation.fulfill()
        }
        wait(for: [expectation], timeout: 0.5)
    }
    
    func test_UserManager_should_return_nil_when_token_is_not_stored() throws {
        // Given - keyChain에 token이 안 저장돼 있을 때
        let expectation = XCTestExpectation(description: "User info not returned")
        sut = UserManager(keyChainService: KeyChainFailureStub())
        
        // When - User 정보를 요청하면
        // Then - nil을 return한다.
        sut.getCurrentUser { user in
            XCTAssertNil(user)
            expectation.fulfill()
        }
        wait(for: [expectation], timeout: 0.5)
    }

}
