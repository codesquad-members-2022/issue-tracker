//
//  StubURLSession.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/16.
//

import Foundation

class StubURLSession: URLSessionProtocol {
    var dummyData: DummyData?

    init(dummy: DummyData) {
        dummyData = dummy
    }

    func dataTask(with url: URL, completionHandler: @escaping (Data?, URLResponse?, Error?) -> Void) -> URLSessionDataTask {
        return SignInStubURLSessionDataTask(dummy: dummyData, completionHandler: completionHandler)
    }
}
