import Foundation
import Alamofire


struct OAuthManager {
    func fetchToken(from url: URL, completion: @escaping (UserToken?) -> Void) {
        if !url.absoluteString.starts(with: "issuetracker://login") {
            return
        }
        guard let code = url.absoluteString.split(separator: "=").last.map({ String($0) }) else {
            return
        }
        requestAccessToken(with: code) { result in
            switch result {
            case .success(let accessToken):
                completion(accessToken)
            case .failure:
                completion(nil)
            }
        }
    }
    
    private func requestAccessToken(with code: String, completion: @escaping (Result<UserToken, NetworkError>) -> Void) {
        let url = RequestURL.accessToken.description
        let privateStorage = PrivateStorage()
        guard let clientId = try? privateStorage.getClientId(),
            let clientSecret = try? privateStorage.getClientSecret() else {
            completion(.failure(.storageKeyNotFound))
            return
        }
        
        let parameters = [
            QueryParameter.clientId.description: "\(clientId)",
            QueryParameter.clientSecret.description: "\(clientSecret)",
            QueryParameter.code.description: code
        ]
        let headers: HTTPHeaders = [
            NetworkHeader.accept.getHttpHeader()
        ]
        
        AF.request(url, method: .post, parameters: parameters, headers: headers).responseDecodable(of: [String: String].self) { (response) in
            switch response.result {
            case .success(let json):
                if let accessToken = json["access_token"] {
                    completion(.success(accessToken))
                }
            case .failure:
                completion(.failure(.tokenNotFound))
            }
        }
    }
}
