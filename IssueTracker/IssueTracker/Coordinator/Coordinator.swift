//
//  Coordinator.swift
//  IssueTracker
//
//  Created by Bibi on 2022/08/09.
//

import Foundation
import UIKit

protocol Coordinator: AnyObject {
    var navigationController: UINavigationController { get }
    var container: Container { get }
    var childCoordinators: [Coordinator] { get set }
    func start()
}
