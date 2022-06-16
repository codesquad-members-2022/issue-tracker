//
//  ContainerWrapper.swift
//  IssueTracker15
//
//  Created by 박진섭 on 2022/06/15.
//

struct ContainerWrapper<T: Resolvable> {
    
    private var container: T?
    
    init(container: T) {
        self.container = container
    }
    
    func regist<P>(instance: P) {
        container?.regist(instance: instance)
    }
    
    func resolve<P>(type:P.Type) -> T.Value? {
        return container?.resolve(type: type)
    }
}
