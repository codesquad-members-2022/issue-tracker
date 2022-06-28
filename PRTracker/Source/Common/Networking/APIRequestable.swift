//
//  Requestable.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

// REST API에서 '행위'를 추상화한 타입
// Resource 외에 URLRequest 정보(Method, Header, Body, etc)와 decoding logic을 담당

protocol APIRequestable {
    associatedtype ModelType
    var request: URLRequest { get }
    func decode(_ data: Data) -> ModelType?
}

// 응답 타입이 JSON으로 Decoding 가능한 경우의 기본 구현
extension APIRequestable where ModelType: Codable {
    func decode(_ data: Data) -> ModelType? {
        return try? JSONDecoder().decode(ModelType.self, from: data)
    }
    
    var defaultHeader: [String: String] {
        return ["Accept": "\(MediaType.json)"]
    }
}
