//
//  TestURLProtocol.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/20.
//

import XCTest

final class URLProtocolStub: URLProtocol {
    override class func canInit(with request: URLRequest) -> Bool {
        true
    }
    
    override class func canonicalRequest(for request: URLRequest) -> URLRequest {
        return request
    }
    
    static var completionHandler: ((URLRequest) -> (HTTPURLResponse, Data?))?
    
    override func startLoading() {
        guard let handler = URLProtocolStub.completionHandler else {
            XCTFail("Handler is not set.")
            return
        }
        
        let (response, data) = handler(request)
        
        if let data = data {
            self.client?.urlProtocol(self, didReceive: response, cacheStoragePolicy: .notAllowed)
            self.client?.urlProtocol(self, didLoad: data)
        } else {
            Log.error("Failed to respond mock data")
        }
        self.client?.urlProtocolDidFinishLoading(self)
    }
    
    override func stopLoading() { }
}
