import Foundation
import Alamofire

enum RequestURL: CustomStringConvertible {
    case authorize
    case accessToken
    case issues
    
    var description: String {
        switch self {
        case .authorize:
            return "https://github.com/login/oauth/authorize"
        case .accessToken:
            return "https://github.com/login/oauth/access_token"
        case .issues:
            return "https://api.github.com/issues"
        }
    }
}

final class NetworkManager {
    public static let shared = NetworkManager()
    private init() {}
    typealias UserToken = String
    
    enum NetworkError: Error {
        case issueNotFound
        case tokenNotFound
        case storageKeyNotFound
    }
    
    func requestCode(completion: @escaping (Result<URL, NetworkError>) -> Void) {
        let scope = "repo,user"
        let urlString = RequestURL.authorize.description
        guard var urlComponents = URLComponents(string: urlString) else {
           return
        }
        
        do {
            let clientId = try PrivateStorage().getClientId()
            urlComponents.queryItems = [
                URLQueryItem(name: "client_id", value: clientId),
                URLQueryItem(name: "scope", value: scope),
            ]
            
            guard let url = urlComponents.url else {
                return
            }
            completion(.success(url))
        } catch {
            completion(.failure(.storageKeyNotFound))
        }
    }
    
    func requestAccessToken(with code: String, completion: @escaping (Result<UserToken, NetworkError>) -> Void) {
        let url = RequestURL.accessToken.description
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
        let urlString = RequestURL.issues.description
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
