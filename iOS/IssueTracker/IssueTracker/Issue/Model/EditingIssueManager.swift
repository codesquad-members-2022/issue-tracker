//
//  EditingIssueManager.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/23.
//

import Foundation

protocol EditingIssueManagable {
    typealias IdData = [String: Int]

    func sendNewIssue(_ newIssueEntity: IssueItem, completion: @escaping (Result<IdData, NetworkError>) -> Void)
    mutating func setURLSession(_ session: URLSessionProtocol)
}

struct EditingIssueManager {
    private var urlSession: URLSessionProtocol

    init(urlSession: URLSessionProtocol = URLSession.shared) {
        self.urlSession = urlSession
    }
}

extension EditingIssueManager: EditingIssueManagable {
    func sendNewIssue(_ newIssueEntity: IssueItem, completion: @escaping (Result<IdData, NetworkError>) -> Void) {
        NetworkService<IdData>.fetchData(
            target: EditingIssueNetworkTarget.sendIssue(newIssue: newIssueEntity),
            urlSession: urlSession,
            completion: completion)
    }

    mutating func setURLSession(_ session: URLSessionProtocol) {
        urlSession = session
    }
 }
