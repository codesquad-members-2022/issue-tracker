//
//  issueNetworkService.swift
//  IssueTracker
//
//  Created by Jason on 2022/06/24.
//

import Foundation

struct IssueManager {
    
    private let urlSession: URLSessionProtocol
    
    init(urlSession: URLSessionProtocol = URLSession.shared) {
        self.urlSession = urlSession
    }

    func getAllIssues(completion: @escaping(Result<[IssueItem], NetworkError>) -> Void) {
        let networkTarget = IssueNetworkTarget.issuesList
        
        var components = URLComponents(string: networkTarget.url) ?? URLComponents()
        components.queryItems = networkTarget.queryItem
        
        guard let url = components.url else {
            return completion(.failure(NetworkError.invalidURL))
        }
        urlSession.dataTask(with: url) { data, _, _ in
            guard let data = data else {
                return completion(.failure(NetworkError.noData))
            }
            guard let decode = try? JSONDecoder().decode([IssueItem].self, from: data) else {
                return completion(.failure(NetworkError.decodingError))
            }
            completion(.success(decode))
        }.resume()
    }
    
}

// MARK: - Data discrimination Network success
extension IssueManager {
    func load(then completion: @escaping([IssueItem]) -> Void) {
        getAllIssues { result in
            switch result {
            case .success(let data):
                completion(data)
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
}
