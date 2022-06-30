//
//  GithubLoginManagerTests.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/21.
//

import XCTest

class GithubLoginManagerTests: XCTestCase {
    
    var sut: GitHubLoginManager!
    
    override func setUpWithError() throws {
        super.setUp()
        
        let config = URLSessionConfiguration.ephemeral
        config.protocolClasses = [URLProtocolStub.self]
        let session = URLSession(configuration: config)
        
        sut = GitHubLoginManager(
            keyChainService: KeyChainSaveStub(),
            networkService: NetworkManger(urlSession: session))
    }
}
