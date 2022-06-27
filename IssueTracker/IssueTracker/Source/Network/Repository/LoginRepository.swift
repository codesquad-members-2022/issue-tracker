//
//  LoginRepository.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/17.
//

import Foundation
import UIKit

final class LoginRepository {

    func requestLoginStatus(_ target: IssueTrackerTarget, _ completion: @escaping (Bool) -> Void) {
        guard let request = Provider.makeURLRequest(with: target) else { return }
        Provider.request(with: request) { response in
            switch response {
            case .success:
                completion(true)
            case .failure:
                completion(false)
            }
        }
    }

    func requestGithubAuthorize() {
        guard let request = Provider.makeURLRequest(with: .requestAuthorizeCode),
              let url = request.url else { return }
        UIApplication.shared.open(url)
    }

    func getGithubAccessToken(_ target: IssueTrackerTarget, _ completion: @escaping (String) -> Void) {
        guard let request = Provider.makeURLRequest(with: target) else { return }
        Provider.request(with: request) { response in
            switch response {
            case .failure:
                // TODO: 토큰 못 없어올때 어떻게 처리할건지
                return
            case .success(let data):
                guard let data = data,
                      let jsonData = try? JSONSerialization.jsonObject(with: data) as? [String: Any],
                      let accessToken = jsonData["access_token"] as? String else { return }
                completion(accessToken)
            }
        }

    }

}
