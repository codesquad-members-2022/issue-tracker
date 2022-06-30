//
//  IssueListModel.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/29.
//

import Foundation
import Alamofire

final class IssueListModel {
    
    private var baseURL: URL? {
        return URL(string: "https://api.github.com")
    }
    
    func requestListIssues() -> DataResponsePublisher<[IssueDTO]>? {
        guard var url = baseURL else {
            return nil
        }
        
        url.appendPathComponent("repos")
        url.appendPathComponent("SangHwi-Back")
        url.appendPathComponent("issue-tracker")
        url.appendPathComponent("issues")
        
        guard
            var request = try? URLRequest(url: url, method: .get),
            let token = UserDefaults.standard.string(forKey: "Access_Token")
        else {
            return nil
        }
        
        request.headers = HTTPHeaders([
            HTTPHeader(name: "Authorization", value: "token \(token)"),
            HTTPHeader(name: "Accept", value: "application/vnd.github.ve+json")
        ])
        
        return Repository
            .shared
            .networkService
            .request(request, urgency: .effective)
    }
}
