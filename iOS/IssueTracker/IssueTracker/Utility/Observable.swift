//
//  Observable.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/20.
//

import Foundation

final class Observable<T> {
    private var observers = [Observer<T>]()

    var value: T {
        didSet {
            notifyObservers()
        }
    }

    init(_ value: T) {
        self.value = value
    }

    func bind(on observer: AnyObject, observerBlock: @escaping (T) -> Void) {
        observers.append(Observer(observer: observer, block: observerBlock))
    }

    func notifyObservers() {
        for observer in observers {
            observer.block(self.value)
        }
    }
}

extension Observable {
    struct Observer<T> {
        weak var observer: AnyObject?
        let block: (T) -> Void
    }
}
