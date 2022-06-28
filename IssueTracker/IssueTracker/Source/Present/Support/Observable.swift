//
//  Observable.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/27.
//

import Foundation

class Observable<T> {

    typealias Listener = (T) -> Void
    var listener: Listener?

    func bind(_ listener: Listener?) {
        self.listener = listener
        listener?(value)
    }

    var value: T {
        didSet {
            listener?(value)
        }
    }

    init(_ value: T) {
        self.value = value
    }
}
