//
//  NetworkTargetProtocol.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/16.
//

import Foundation

protocol NetworkTargetProtocol {
    var url: String { get }
    var queryItem: [URLQueryItem]? { get }
    var method: String? { get }
    var body: Data? { get }
}
