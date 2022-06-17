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

class IssueTrackingRepository {

    func requestIssues(url: String,
                       completion: @escaping (Result<[Issue]?, NetworkError>) -> Void) {
        guard let requestURL = URL(string: url) else {
            return completion(.failure(.invalidUrlError))
        }
        var result: [Issue] = []
        let session = URLSession.shared

        session.dataTask(with: requestURL) {
            data, response, error in
            do {
                guard error == nil else {
                    return completion(.failure(.cantReachedServerError))
                }

                if let data = data,
                   let response = response as? HTTPURLResponse,
                   response.statusCode == 200 {
                    guard let issueResponse: IssueResponse = DecodeManagerImplement.decodeJson(data: data) else { return }
//                    let issueResponse = try JSONDecoder().decode(IssueResponse.self, from: data)
                    result = issueResponse.body
                }
            } catch {
                completion(.failure(.invalidJsonError))
            }
        }

        return completion(.success(result))
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
            guard let issueList: IssueResponse = DecodeManagerImplement.decodeJson(data: jsonData) else { return nil }
//            let issueList = try JSONDecoder().decode(IssueResponse.self, from: jsonData)
            return issueList.body
        } catch {
            // TODO: - error 처리
            return nil
        }
    }
}
