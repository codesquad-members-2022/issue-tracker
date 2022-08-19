//
//  ReposCoordinator.swift
//  IssueTracker
//
//  Created by Bibi on 2022/08/09.
//

import Foundation
import UIKit

protocol ReposCoordinatorDelegate {
    func didSelect(repository: Repository)
}

class ReposCoordinator: Coordinator {
    var container: Container
    
    var childCoordinators: [Coordinator] = []
    
    private let navigationController: UINavigationController
    
    var delegate: ReposCoordinatorDelegate?
    
    init(navigationController: UINavigationController, container: Container) {
        self.navigationController = navigationController
        self.container = container
    }
    
    func start() {
        // MARK: VC초기화시 인스턴스 container에서 받아오기
        guard let viewController: ReposViewController = container.resolve() else {
            return
        }
        viewController.delegate = self
        
        self.navigationController.viewControllers = [viewController]
    }
}

extension ReposCoordinator: ReposViewControllerDelegate {
    func showIssue(didSelectRowAt indexPath: IndexPath) {
        print("showIssue")
        guard let model: ReposModel = container.resolve() else {
            return
        }
        let selectedItem = model.getViewData(index: indexPath.row)
        self.delegate?.didSelect(repository: selectedItem)
    }
}

