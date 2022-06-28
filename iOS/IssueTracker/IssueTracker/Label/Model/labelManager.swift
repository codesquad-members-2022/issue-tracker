//
//  labelManager.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import Foundation

protocol LabelManagable {
    func getLabels(completion: @escaping (Result<[LabelItem], NetworkError>) -> Void)
}

struct LabelManager {
    private let urlSession: URLSessionProtocol

    init(urlSession: URLSessionProtocol = URLSession.shared) {
        self.urlSession = urlSession
    }
}

extension LabelManager: LabelManagable {
    func getLabels(completion: @escaping (Result<[LabelItem], NetworkError>) -> Void) {
        NetworkService.fetchData(target: LabelNetworkTarget.requestLabels, urlSession: urlSession, completion: completion)
    }
}
