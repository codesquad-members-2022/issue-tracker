//
//  TestURLProtocol.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/20.
//

import Foundation

final class TestURLProtocol: URLProtocol {
    override class func canInit(with request: URLRequest) -> Bool {
        true
    }
    
    override class func canonicalRequest(for request: URLRequest) -> URLRequest {
        return request
    }
}
