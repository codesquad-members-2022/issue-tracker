import Foundation
import Alamofire

typealias UserToken = String

enum GitHubError: Error {
    case issueNotFound
}

struct GitHubService {
    
    func requestIssues(accessToken: String, completion: @escaping (Result<[Issue], GitHubError>) -> Void) {
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
