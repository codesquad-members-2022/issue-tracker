//
//  FetchIssueCardRepository.swift
//  IssueTracker
//
//  Created by Kai Kim on 2022/06/30.
//

import Foundation

final class ViewFilteredIssueCardRepository: ViewIssueCardRepository {

    var endPoint: EndPoint

    init(endPoint: EndPoint = EndPoint(urlConfigure: FetchOpenIssueListURLConfiguration(), method: .GET, body: nil)) {
        self.endPoint = endPoint
    }

    func fetchIssueCard(completion: @escaping (IssueCardArrayDTO?) -> Void) {
        NetworkService.request(endPoint: endPoint) { result in
            switch result {
            case .success(let data):
                let decoder = Decoder<IssueCardArrayDTO>()
                let issuecards =  decoder.decode(data: data)
                completion(issuecards)
            case .failure(let error):
                print(error)
            }
        }
    }

}
