//
//  IssueUpdateModel.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/29.
//

import Foundation
import Alamofire

class IssueUpdateModel {
    
    private var baseURL: URL? {
        return URL(string: "https://api.github.com")
    }
    private var param: Any?
    
    func requestCloseIssue() -> DataResponsePublisher<IssueDTO>? {
        
        guard var request = getBaseUpdateURLRequest(), var dto = param as? IssueDTO else {
            return nil
        }
        
        dto.state = "close"
        request.httpBody = try? JSONEncoder().encode(dto)
        
        return Repository
            .shared
            .networkService
            .request(request, urgency: .effective)
    }
    
    private func getBaseUpdateURLRequest() -> URLRequest? {
        guard var url = baseURL, let dto = param as? IssueDTO else {
            return nil
        }
        
        url.appendPathComponent("repos")
        url.appendPathComponent("SangHwi-Back")
        url.appendPathComponent("issue-tracker")
        url.appendPathComponent("issues")
        url.appendPathComponent("\(dto.number)")
        
        guard
            var request = try? URLRequest(url: url, method: .post),
            let token = UserDefaults.standard.string(forKey: "Access_Token")
        else {
            return nil
        }
        
        request.headers = HTTPHeaders([
            HTTPHeader(name: "Authorization", value: "token \(token)"),
            HTTPHeader(name: "Accept", value: "application/vnd.github.ve+json")
        ])
        
        return request
    }
}
