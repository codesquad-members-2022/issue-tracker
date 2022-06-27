//
//  DIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/27.
//

import UIKit

protocol DIContainer {
	func makeCoordinator(navigationController: UINavigationController) -> Coordinator
}
