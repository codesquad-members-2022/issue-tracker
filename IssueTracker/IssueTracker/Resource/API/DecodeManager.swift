//
//  DecodeManager.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/14.
//

import Foundation

protocol DecodeManager {
    static func decodeJson<T: Codable>(data: Data) -> T?
}

final class DecodeManagerImplement: DecodeManager {

    static func decodeJson<T: Codable>(data: Data) -> T? {
        do {
            let result = try JSONDecoder().decode(T.self, from: data)
            return result
        } catch {
            guard let error = error as? DecodingError else { return nil }

            switch error {
            case .dataCorrupted(let context):
                print(context.codingPath, context.debugDescription, context.underlyingError ?? "", separator: "\n")
                return nil
            default :
                return nil
            }
        }
    }

}
