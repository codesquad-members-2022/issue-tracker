//
// Created by 백상휘 on 2022/06/14.
//

import Foundation
import Combine

class TestUseCase: UseCaseResponsible {
    
    static let shared = TestUseCase()
    private var storeCancellable: Set<AnyCancellable> = []
    
    private var cancellable = Set<AnyCancellable>()
    
    func requestFromUseCase(_ completionBlock: @escaping (Any?)->Void) {
        RequestModel.request(self).result().sink { result in
            switch result {
            case .success(let data):
                completionBlock(data)
            case .failure:
                print("Failure")
            }
        }.store(in: &storeCancellable)
    }
    
    func testRequest(_ completionBlock: @escaping (Any?)->Void) {
        RequestModel.networkRequest(urgency: .urgent)?.sink(receiveValue: { response in
            if let error = response.error {
                completionBlock(error)
            }
            
            completionBlock(response.data)
        })
        .store(in: &cancellable)
    }
}
