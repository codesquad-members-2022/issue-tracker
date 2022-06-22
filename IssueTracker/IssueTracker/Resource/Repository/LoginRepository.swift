//
//  LoginRepository.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/17.
//

import Foundation
import UIKit

final class LoginRepository {

    func requestGithubAuthorize() {
        guard let request = Provider.makeURLRequest(with: .requestAuthorizeCode),
              let url = request.url else { return }
        UIApplication.shared.open(url)
    }

    func getGithubAccessToken(_ target: IssueTrackerTarget, _ completion: @escaping (String) -> Void) {
        guard let request = Provider.makeURLRequest(with: target) else { return }
        Provider.request(with: request) { data in
            guard let jsonData = try? JSONSerialization.jsonObject(with: data) as? [String: Any],
                  let accessToken = jsonData["access_token"] as? String else { return }
            completion(accessToken)
        }

    }

}
