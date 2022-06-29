//
//  Storage.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/29.
//

import Foundation

class Storage<Key: Hashable, Value> {
    var value: [Key: Value]
    
    init(value: [Key: Value] = [:]) {
        self.value = value
    }
    
    func remove() {
        self.value = [:]
    }
}
