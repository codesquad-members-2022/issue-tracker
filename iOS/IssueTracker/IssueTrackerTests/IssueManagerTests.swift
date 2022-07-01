//
//  IssueManagerTests.swift
//  IssueTrackerTests
//
//  Created by Jason on 2022/07/01.
//

import XCTest
@testable import IssueTracker

final class IssueManagerTests: XCTestCase {
    
    private var issueManager: IssueManager!
    
    override func setUp() {
        super.setUp()
        
        self.issueManager = IssueManager()
    }
    
    override func tearDown() {
        super.tearDown()
        
    }
}
