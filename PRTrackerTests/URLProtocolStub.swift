//
//  TestURLProtocol.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/20.
//

import Foundation

final class URLProtocolStub: URLProtocol {
    override class func canInit(with request: URLRequest) -> Bool {
        true
    }
    
    override class func canonicalRequest(for request: URLRequest) -> URLRequest {
        return request
    }
    
    static var testURLs = [URL?: Data]()
    
    override func startLoading() {
        if let url = request.url, let data = URLProtocolStub.testURLs[url] {
            self.client?.urlProtocol(self, didLoad: data)
        }
        self.client?.urlProtocolDidFinishLoading(self)
    }
    
    override func stopLoading() { }
}
