import Foundation
import Alamofire

final class NetworkManager {
    enum NetworkError: Error {
        case issueNotFound
        case tokenNotFound
        case storageKeyNotFound
    }
    
    public static let shared = NetworkManager()
    private init() {}
    
    typealias UserToken = String
    
    func requestAccessToken(with code: String, completion: @escaping (Result<UserToken, NetworkError>) -> Void) {
        let url = "https://github.com/login/oauth/access_token"
        
        let privateStorage = PrivateStorage()
        guard let clientId = try? privateStorage.getClientId(),
            let clientSecret = try? privateStorage.getClientSecret() else {
            completion(.failure(.storageKeyNotFound))
            return
        }
        
        let parameters = [
            "client_id": "\(clientId)",
            "client_secret": "\(clientSecret)",
            "code": code
        ]
        let headers: HTTPHeaders = [
            "Accept": "application/json"
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
    
    func requestIssues(accessToken: String, completion: @escaping (Result<[Issue], NetworkError>) -> Void) {
        let urlString = "https://api.github.com/issues"
        let headers: HTTPHeaders = [
            "Accept": "application/vnd.github.v3+json",
            "Authorization": "token \(accessToken)"
        ]
        AF.request(urlString, method: .get, headers: headers)
            .responseDecodable(of: [Issue].self) { (response) in
            switch response.result {
            case let .success(decodeData):
                completion(.success(decodeData))
            case .failure:
                completion(.failure(.issueNotFound))
            }
        }
    }
}
