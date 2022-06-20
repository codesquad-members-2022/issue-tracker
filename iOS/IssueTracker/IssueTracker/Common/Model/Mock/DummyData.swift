//
//  DummyData.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/20.
//

import Foundation

struct DummyData {
    let data: Data?
    let response: URLResponse?
    let error: Error?
    var completionHandler: ((Data?, URLResponse?, Error?) -> Void)? = nil

    func completion() {
        completionHandler?(data, response, error)
    }
}
