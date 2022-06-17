//
// Created by 백상휘 on 2022/06/14.
//

import Foundation

class TestViewModel: CommonViewModel {
    
    var output: (Any?, ViewBindable) -> Void
    var container: ContainerWrapper<UseCaseContainer> = ContainerWrapper(container: UseCaseContainer.shard)
    
    init(_ output: @escaping (Any?, ViewBindable) -> Void) {
        self.output = output
    }
    
    // Simulation Button request REST-API
    func request(_ bindable: ViewBindable, param: Any?) {
        TestUseCase().requestFromUseCase { result in
            self.output(result, bindable)
        }
    }
}

protocol CommonViewModel {
    var output: (Any?, ViewBindable) -> Void { get }
    func request(_ bindable: ViewBindable, param: Any?)
}
