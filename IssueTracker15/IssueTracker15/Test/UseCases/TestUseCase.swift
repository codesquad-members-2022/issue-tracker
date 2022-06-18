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
        RequestModel
            .networkRequest(urgency: .urgent)?
            .value()
            .subscribe(on: DispatchQueue.global())
            .sink(
                receiveCompletion: { completion in
                    switch completion {
                    case .failure(let error):
                        print(error)
                    case .finished:
                        print("haha")
                    }
                },
                receiveValue: { data in
                    print("kaka")
                    completionBlock(data)
                }
            )
            .store(in: &cancellable)
    }
}
