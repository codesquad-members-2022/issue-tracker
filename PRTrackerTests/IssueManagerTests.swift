//
//  PullManagerTests.swift
//  PRTrackerTests
//
//  Created by Bumgeun Song on 2022/06/20.
//

import XCTest


class IssueManagerTests: XCTestCase {
    

    let mockSession: URLSession = {
        let config = URLSessionConfiguration.ephemeral
        config.protocolClasses = [URLProtocolStub.self]
        return URLSession(configuration: config)
    }()
    
    override func setUpWithError() throws {
        super.setUp()
    }
    
    func test_IssueManager_should_return_Issue_when_token_is_stored() throws {
        // Given - keyChain에 token이 저장돼 있을 때
        let sut = IssueManager(keyChainService: KeyChainSuccessStub(),
                           networkService: NetworkManger(urlSession: mockSession))
        
        guard let url = URL(string: BaseURL.issues) else {
            Log.error("Wrong Base URL: \(BaseURL.issues)")
            return 
        }
        
        guard let dummyData = DummyResponse.issues.data else {
            XCTFail("Cannot find \(DummyResponse.issues)")
            return
        }
        
        // URLSession에 dummy Reponse 주입
        URLProtocolStub.completionHandler = { _ in
            let response = HTTPURLResponse(url: url, statusCode: 200, httpVersion: nil, headerFields: nil)!
            return (response, dummyData)
        }
        
        // When - Issue 정보를 요청하면
        sut.getIssues { issues in
            guard let issues = try? XCTUnwrap(issues) else { return }
            
            // Then - 토큰의 사용자가 가진 Issue 정보를 return한다.
            XCTAssertEqual(issues.count, 4)
            XCTAssertEqual(issues[0].id, 1276771930)
            XCTAssertEqual(issues[0].title, "[22.06.20] 리팩토링 챕터 3 시작")
            XCTAssertEqual(issues[0].creator?.userName, "BumgeunSong")
        }
    }
    
    func test_IssueManager_should_return_nil_when_token_is_not_stored() throws {
        // Given - keyChain에 token이 안 저장돼 있을 때
        let sut = IssueManager(keyChainService: KeyChainFailureStub(),
                           networkService: NetworkManger(urlSession: mockSession))
        
        // When - Issue 정보를 요청하면
        // Then - nil을 return한다.
        sut.getIssues { issues in
            XCTAssertNil(issues)
        }
    }
    
    
}
