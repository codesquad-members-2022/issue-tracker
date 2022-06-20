//
//  IssueTrackingRepository.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/17.
//

import Foundation

protocol IssueTracking {
    func requestIssues(url: String, completion: @escaping (Result<[Issue]?, NetworkError>) -> Void )
    func getIssuesOnFailure() -> [Issue]?
}

final class IssueTrackingRepository {

    func requestIssues(with target: IssueTrackerTarget,
                       completion: @escaping (Result<[Issue]?, NetworkError>) -> Void) {
        guard let request = Provider.makeURLRequest(with: target)
        else { return completion(.failure(.invalidTarget)) }

        Provider.request(with: request) { data in
            guard let issueList = DecodeManagerImplement
                    .decodeJson(data: data, type: [Issue].self) else { return }
            return completion(.success(issueList))
        }
    }
    
    // MARK: - Local에 JSON 파일로 [Issue] 를 로딩
    func getIssuesOnFailure() -> [Issue]? {
        let fileName: String = "Issue"
        let extensionType = "json"

        guard let fileLocation = Bundle.main.url(
            forResource: fileName,
            withExtension: extensionType
        ) else { return nil }

        do {
            let jsonData = try Data(contentsOf: fileLocation)
            guard let issueList: IssueResponse = DecodeManagerImplement.decodeJson(data: jsonData, type: IssueResponse.self) else { return nil }
            return issueList.body
        } catch {
            // TODO: - error 처리
            return nil
        }
    }
}
