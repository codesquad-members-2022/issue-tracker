//
//  Observable.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/15.
//

import Foundation

final class Observable<T> {
    typealias EventHandler = (T) -> Void

    struct Observer {
        weak var identifier: AnyObject?
        var handler: EventHandler
    }

    var observers: [Observer] = []

    private var value: T {
        didSet {
            notifyObservers()
        }
    }

    init(_ value: T) {
        self.value = value
    }

    func bind(to observer: AnyObject? = nil, with eventHandler: @escaping EventHandler) {
        observers.append(Observer(identifier: observer, handler: eventHandler))
        eventHandler(value)
    }

    func notifyObservers() {
        observers.forEach { observer in
            observer.handler(value)
        }
    }
}
