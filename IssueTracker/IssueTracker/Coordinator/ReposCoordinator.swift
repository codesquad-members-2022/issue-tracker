//
//  ReposCoordinator.swift
//  IssueTracker
//
//  Created by Bibi on 2022/08/09.
//

import Foundation
import UIKit

protocol ReposCoordinatorDelegate {
    
}

class ReposCoordinator {
    
    private let navigationController: UINavigationController
    
    var delegate: ReposCoordinatorDelegate?
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
    }
}


