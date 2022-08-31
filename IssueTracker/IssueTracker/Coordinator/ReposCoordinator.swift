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
    
    let navigationController: UINavigationController
    
    var delegate: ReposCoordinatorDelegate?
    
    init(navigationController: UINavigationController, container: Container) {
        self.navigationController = navigationController
        self.container = container
    }
    
    func start() {
        guard let viewController: ReposViewController = container.resolve() else {
            return
        }
        viewController.delegate = self
        
        // MARK: 아래 코드가 뷰에 화면을 보여주는 역할 - 둘 중 하나 사용
        self.navigationController.viewControllers = [viewController]
//        navigationController.pushViewController(viewController, animated: true)
    }
}

extension ReposCoordinator: ReposViewControllerDelegate {
    func showIssue(didSelectRowAt indexPath: IndexPath) {
        guard let model: ReposModel = container.resolve() else {
            return
        }
        let selectedRepo = model.getViewData(index: indexPath.row)
        self.delegate?.didSelect(repository: selectedRepo)
    }
}

