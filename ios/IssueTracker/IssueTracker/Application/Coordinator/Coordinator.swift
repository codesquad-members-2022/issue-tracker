//
//  Coordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/22.
//

import Foundation

protocol Coordinator: AnyObject {
    func start(with deepLink: DeepLink?)
}
