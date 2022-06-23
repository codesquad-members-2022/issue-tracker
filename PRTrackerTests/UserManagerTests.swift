//
//  UserManagerTests.swift
//  PRTrackerTests
//
//  Created by Bumgeun Song on 2022/06/20.
//

import XCTest


class UserManagerTests: XCTestCase {

    let mockSession: URLSession = {
        let config = URLSessionConfiguration.ephemeral
        config.protocolClasses = [URLProtocolStub.self]
        return URLSession(configuration: config)
    }()
    
    override func setUpWithError() throws {
        super.setUp()
    }
    
    func test_UserManager_should_return_User_when_token_is_stored() throws {
        // Given - keyChain에 token이 저장돼 있을 때
        let sut = UserManager(keyChainService: KeyChainSuccessStub(),
                          networkService: NetworkManger(urlSession: mockSession))
        
        guard let url = URL(string: BaseURL.user) else {
            Log.error("Wrong Base URL: \(BaseURL.user)")
            return
        }
        
        guard let dummyData = DummyResponse.user.data else {
            XCTFail("Cannot find \(DummyResponse.user)")
            return
        }
        
        // URLSession에 dummy Reponse와 dummy Data 주입
        URLProtocolStub.completionHandler = { _ in
            let response = HTTPURLResponse(url: url, statusCode: 200, httpVersion: nil, headerFields: nil)!
            return (response, dummyData)
        }
        
        // When - User 정보를 요청하면
        sut.getCurrentUser { user in
            
            // Then - 토큰에 해당하는 User 정보를 return한다.
            XCTAssertEqual(user?.id, 17468015)
            XCTAssertEqual(user?.userName, "BumgeunSong")
            XCTAssertEqual(user?.reposURL, "https://api.github.com/users/BumgeunSong/repos")
        }
    }
    
    func test_UserManager_should_return_nil_when_token_is_not_stored() throws {
        // Given - keyChain에 token이 안 저장돼 있을 때
        let sut = UserManager(keyChainService: KeyChainFailureStub(),
                          networkService: NetworkManger(urlSession: mockSession))
        
        // When - User 정보를 요청하면
        sut.getCurrentUser { user in
            // Then - nil을 return한다.
            XCTAssertNil(user)
        }
    }

}
