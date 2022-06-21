//
//  GithubLoginManagerTests.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/21.
//

import XCTest

class GithubLoginManagerTests: XCTestCase {
    
    var sut: GitHubLoginManager!
    var uiApplication: UIApplicationStub!
    
    override func setUpWithError() throws {
        super.setUp()
        
        let config = URLSessionConfiguration.ephemeral
        config.protocolClasses = [URLProtocolStub.self]
        let session = URLSession(configuration: config)
        
        sut = GitHubLoginManager(
            keyChainService: KeyChainSaveStub(),
            networkService: NetworkManger(urlSession: session))
    }

    func test_should_open_url_with_clientId_and_scope() throws {
        // TODO: UIApplication을 mocking하는 방법? Mock을 하지 않는 게 이득일까?
    }
    
}
