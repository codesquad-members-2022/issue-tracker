import Foundation
import Alamofire

typealias UserToken = String

enum NetworkError: Error {
    case issueNotFound
    case tokenNotFound
    case storageKeyNotFound
}

final class NetworkManager {
    public static let shared = NetworkManager()
    private init() {}
    
    func requestCode(completion: @escaping (Result<URL, NetworkError>) -> Void) {
        let scope = "repo,user"
        let urlString = RequestURL.authorize.description
        guard var urlComponents = URLComponents(string: urlString) else {
           return
        }
        
        do {
            let clientId = try PrivateStorage().getClientId()
            urlComponents.queryItems = [
                URLQueryItem(name: QueryParameter.clientId.description, value: clientId),
                URLQueryItem(name: QueryParameter.scope.description, value: scope),
            ]
            
            guard let url = urlComponents.url else {
                return
            }
            completion(.success(url))
        } catch {
            completion(.failure(.storageKeyNotFound))
        }
    }
    

    func requestIssues(accessToken: String, completion: @escaping (Result<[Issue], NetworkError>) -> Void) {
        let urlString = RequestURL.issues.description
        let headers: HTTPHeaders = [
            NetworkHeader.acceptV3.getHttpHeader(),
            NetworkHeader.authorization(accessToken: accessToken).getHttpHeader()
        ]
        
        let decoder = JSONDecoder()
        decoder.keyDecodingStrategy = .convertFromSnakeCase
        
        AF.request(urlString, method: .get, headers: headers)
            .responseDecodable(of: [Issue].self, decoder: decoder) { (response) in
            switch response.result {
            case let .success(decodeData):
                completion(.success(decodeData))
            case .failure:
                completion(.failure(.issueNotFound))
            }
        }
    }
}
