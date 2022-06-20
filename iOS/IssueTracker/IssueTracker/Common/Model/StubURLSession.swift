//
//  StubURLSession.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/16.
//

import Foundation

protocol URLSessionProtocol {
    func dataTask(with url: URL, completionHandler: @escaping (Data?, URLResponse?, Error?) -> Void) -> URLSessionDataTask
}

extension URLSession: URLSessionProtocol {}

struct DummyData {
    let data: Data?
    let response: URLResponse?
    let error: Error?
    var completionHandler: ((Data?, URLResponse?, Error?) -> Void)? = nil

    func completion() {
        completionHandler?(data, response, error)
    }
}

class StubURLSession: URLSessionProtocol {
    var dummyData: DummyData?

    init(dummy: DummyData) {
        dummyData = dummy
    }

    func dataTask(with url: URL, completionHandler: @escaping (Data?, URLResponse?, Error?) -> Void) -> URLSessionDataTask {
        return SignInStubURLSessionDataTask(dummy: dummyData, completionHandler: completionHandler)
    }
}

class SignInStubURLSessionDataTask: URLSessionDataTask {
    var dummyData: DummyData?

    init(dummy: DummyData?, completionHandler: ((Data?, URLResponse?, Error?) -> Void)?) {
        self.dummyData = dummy
        self.dummyData?.completionHandler = completionHandler
    }

    override func resume() {
        dummyData?.completion()
    }
}
