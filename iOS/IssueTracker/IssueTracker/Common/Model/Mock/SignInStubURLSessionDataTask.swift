//
//  SignInStubURLSessionDataTask.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/20.
//

import Foundation

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
