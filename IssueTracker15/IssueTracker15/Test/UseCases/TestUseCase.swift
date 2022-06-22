//
// Created by 백상휘 on 2022/06/14.
//

import Foundation
import Combine

class TestUseCase: UseCaseResponsible {
    
    static let shared = TestUseCase()
    private var cancellables: Set<AnyCancellable> = []
    
    func request(_ completionBlock: @escaping (Any?) -> Void) {
        RequestModel
            .networkRequest(urgency: .urgent)?
            .result()
            .subscribe(on: DispatchQueue.global())
            .sink(receiveValue: { result in
                switch result {
                case .success(let data):
                    completionBlock(data)
                case .failure(let error):
                    completionBlock(error)
                }
            })
            .store(in: &cancellables)
    }
}
