//
//  NetworkError.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/27.
//

import Foundation

enum NetworkError: Error {
    case networkFailure(error: Error)
    case failedParsingHTTPResponse
    case unexpectedStatusCode(_ statusCode: Int)
    case missingData
    case failedDecoding(type: String)
}

extension NetworkError: LocalizedError {
    var errorDescription: String? {
        switch self {
            
        case .networkFailure(let error):
            return NSLocalizedString(error.localizedDescription, comment: "Failed Networking")
        case .failedParsingHTTPResponse:
            return NSLocalizedString("Cannot parse HTTP response", comment: "Cannot parse HTTP response")
        case .unexpectedStatusCode(let code):
            return NSLocalizedString("Unexpected Status Code: \(code)", comment: "Unexpected Status Code")
        case .missingData:
            return NSLocalizedString("Missing data", comment: "Missing data")
        case .failedDecoding(let type):
            return NSLocalizedString("Decoding for \(type) failed", comment: "Failed Decoding")
        }
    }
}
