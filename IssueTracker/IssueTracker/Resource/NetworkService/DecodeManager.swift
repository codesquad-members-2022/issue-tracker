//
//  DecodeManager.swift
//  IssueTracker
//
//  Created by YEONGJIN JANG on 2022/06/14.
//

import Foundation

protocol DecodeManager {
    func requestIssues(url: String) -> [Issue]?
    func getIssuesOnFailure() -> [Issue]?
}

class DecodeManagerImpl: DecodeManager {
    func requestIssues(url: String) -> [Issue]? {
        guard let requestURL = URL(string: url) else { return getIssuesOnFailure() }
        var result: [Issue] = []
        let session = URLSession.shared

        session.dataTask(with: requestURL) { data, response, error in
            do {
                guard error == nil else {
                    return print(error?.localizedDescription ?? "")
                }

                if let data = data,
                   let response = response as? HTTPURLResponse,
                   response.statusCode == 200 {
                    let issueResponse = try JSONDecoder().decode(IssueResponse.self, from: data)
                    result = issueResponse.body
                }
            } catch let err {
                print("Decoding Error")
                print(err.localizedDescription)
            }
        }

        return result
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
            let issueList = try JSONDecoder().decode(IssueResponse.self, from: jsonData)
            return issueList.body
        } catch {
            return nil
        }
    }
}
