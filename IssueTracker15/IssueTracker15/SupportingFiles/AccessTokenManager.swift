//
//  AccessTokenManager.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/23.
//

import Foundation
import Alamofire

struct AccessTokenManager {
    
    func requestAccessToken(code: String?, onCompleted: @escaping (Any) -> Void) {
        guard let code = code else { return }
        let postURL = Bundle.main.gitLoginAccessTokenURL
        
        // code와 함꼐 parameter를 Post
        let parameters = [
            OAuthLoginSourceKey.GitHub.Query.clientID.rawValue: Bundle.main.gitClientID,
            OAuthLoginSourceKey.GitHub.Query.clientSecret.rawValue: Bundle.main.gitClientSecret,
            OAuthLoginSourceKey.GitHub.Query.code.rawValue: code]

        let headers: HTTPHeaders = ["Accept": "application/json"]
        
        AF
            .request(postURL, method: .post, parameters: parameters, headers: headers)
            .responseDecodable { (response: DataResponse<AccessInfo, AFError>) in
                switch response.result {
                // 성공시 accessToken이 담긴 구조체를 담아 보낸다.
                case let .success(accessInfo):
                    onCompleted(accessInfo)
                // 실패시 에러를 담아 보낸다.
                case let .failure(error):
                    onCompleted(error)
            }
        }
    }
}
