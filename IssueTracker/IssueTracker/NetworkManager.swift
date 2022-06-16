import Foundation
import Alamofire

final class NetworkManager {
    enum NetworkError: Error {
        case issueNotFound
    }
    
    public static let shared = NetworkManager()
    private init() {}
    
    func requestAccessToken(with code: String) {
        let url = "https://github.com/login/oauth/access_token"
        
        let parameters = ["client_id": "\(PrivateStorage.clientId)",
                          "client_secret": "\(PrivateStorage.clientSecret)",
                          "code": code]
        let headers: HTTPHeaders = ["Accept": "application/json"]
        
        AF.request(url, method: .post, parameters: parameters, headers: headers).responseDecodable(of: [String: String].self) { (response) in
            switch response.result {
            case let .success(json):
                if let dic = json as? [String: String],
                   let accessToken = dic["access_token"] {
                    GithubUserDefaults.setToken(uid: accessToken)
                }
            case let .failure(error):
                print(error)
            }
        }
    }
    
    func requestIssues(accessToken: String, completion: @escaping (Result<[Issue], NetworkError>) -> Void) {
        let urlString = "https://api.github.com/issues"
        let headers: HTTPHeaders = [
            "Accept": "application/vnd.github.v3+json",
            "Authorization": "token \(accessToken)"
        ]
        AF.request(urlString, method: .get, headers: headers)
            .responseDecodable(of: [Issue].self) { (response) in
                print("response: \(response)")
            switch response.result {
            case let .success(decodeData):
                print(decodeData)
                completion(.success(decodeData))
            case let .failure(error):
                print(error)
                completion(.failure(.issueNotFound))
            }
        }
    }
}
