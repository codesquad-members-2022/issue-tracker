//
//  issueNetworkService.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/24.
//

import Foundation

protocol IssueManagable {
    func load(then completion: @escaping(Result<[IssueItem], NetworkError>) -> Void)
}

struct IssueManager {
    private let urlSession: URLSessionProtocol
    
    init(urlSession: URLSessionProtocol = URLSession.shared) {
        self.urlSession = urlSession
    }
}

// MARK: - Data discrimination Network success
extension IssueManager: IssueManagable {
    func load(then completion: @escaping(Result<[IssueItem], NetworkError>) -> Void) {
        NetworkService<[IssueItem]>.fetchData(
            target: IssueNetworkTarget.issuesList,
            urlSession: urlSession,
            completion: completion)
    }
}
