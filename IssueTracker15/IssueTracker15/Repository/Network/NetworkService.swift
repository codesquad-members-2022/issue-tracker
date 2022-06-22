//
//  Created by 백상휘 on 2022/06/15.
//

import Foundation
import Alamofire

class NetworkService<T: Codable> {
    
    private var urgentSession = Session(requestQueue: DispatchQueue.global(qos: .userInteractive))
    private var effectiveSession = Session(requestQueue: DispatchQueue.global(qos: .background))
    private var manualSession = Session(startRequestsImmediately: false)
    
    func request(_ request: URLRequest, urgency: RequestUrgency) -> DataResponsePublisher<T>? {
        guard let method = request.method else {
            return nil
        }
        
        switch method {
        case .get, .post:
            return getSession(urgency).request(request).publishDecodable(type: T.self)
        default:
            return nil
        }
    }
    
    private func getSession(_ urgency: RequestUrgency) -> Session {
        switch urgency {
        case .urgent:
            return urgentSession
        case .effective:
            return effectiveSession
        case .manual:
            return manualSession
        }
    }
}

/// https://docs.github.com/en/rest/issues/issues#list-issues-assigned-to-the-authenticated-user--status-codes
enum ResponseError: Int, Error, CaseIterable {
    case NotModified = 304
    case ResourceNotFound = 404
    case ValidationFailed = 422
    
    func getMessage() -> String {
        switch self {
        case .NotModified:
            return "[304][Not Modified] No Need to request repeatedly."
        case .ResourceNotFound:
            return "[404][Resource Not Found] Can not find any resource because of URL or Path."
        case .ValidationFailed:
            return "[422][Validation Failed] Request Denied. Stop request and Contact Service Provider."
        }
    }
}

enum RequestUrgency {
    case urgent
    case effective
    case manual
}

private extension Int {
    func getResponseError() -> ResponseError? {
        guard self != 200 else { return nil }
        
        for error in ResponseError.allCases where self == error.rawValue {
            return error
        }
        
        return nil
    }
}
