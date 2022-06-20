//
//  URLSessionProtocol.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/20.
//

import Foundation

protocol URLSessionProtocol {
    func dataTask(with url: URL, completionHandler: @escaping (Data?, URLResponse?, Error?) -> Void) -> URLSessionDataTask
}
