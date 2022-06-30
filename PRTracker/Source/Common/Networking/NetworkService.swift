//
//  NetworkService.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/17.
//

import Foundation

protocol NetworkService {
    func execute<T: APIRequestable>(_ request: T, completion: @escaping (Result<T.ModelType, NetworkError>) -> Void)
}

struct NetworkManger: NetworkService {

    let session: URLSession

    init(urlSession: URLSession = URLSession.shared) {
        self.session = urlSession
    }
    
    func execute<T: APIRequestable>(_ request: T, completion: @escaping (Result<T.ModelType, NetworkError>) -> Void) {
        
        session.dataTask(with: request.request) { data, response, error in
            if let error = error {
                return completion(.failure(.networkFailure(error: error)))
            }
            
            guard let statusCode = (response as? HTTPURLResponse)?.statusCode else {
                return completion(.failure(.failedParsingHTTPResponse))
            }
            
            guard (200..<300).contains(statusCode) else {
                return completion(.failure(.unexpectedStatusCode(statusCode)))
            }
            
            guard let data = data else {
                return completion(.failure(.missingData))
            }
            
            
            guard let decoded = request.decode(data) else {
                print(data.prettyPrintedJSONString!)
                
                return completion(.failure(.failedDecoding(type: "\(T.ModelType.self)")))
            }
            
            completion(.success(decoded))
        }.resume()
    }
}

extension Data {
    var prettyPrintedJSONString: NSString? { /// NSString gives us a nice sanitized debugDescription
        guard let object = try? JSONSerialization.jsonObject(with: self, options: []),
              let data = try? JSONSerialization.data(withJSONObject: object, options: [.prettyPrinted]),
              let prettyPrintedString = NSString(data: data, encoding: String.Encoding.utf8.rawValue) else { return nil }

        return prettyPrintedString
    }
}
