//
//  NewIssueCoordinator.swift
//  IssueTracker
//
//  Created by Bibi on 2022/08/09.
//

import Foundation
import UIKit

protocol NewIssueCoordinatorDelegate {
    func goBackToIssueVC(repo: Repository)
}

class NewIssueCoordinator: Coordinator {
    
    var delegate: NewIssueCoordinatorDelegate?
    
    var navigationController: UINavigationController
    var childCoordinators: [Coordinator] = []
    
    private var repo: Repository
    private var container: Container
    
    init(navigationController: UINavigationController, container: Container, repo: Repository) {
        self.navigationController = navigationController
        self.container = container
        self.repo = repo
    }
    
    func start() {
        let modelEnvironment = NewIssueModelEnvironment { [weak self] newIssueFormat, completion in
            self?.container.environment.issueService.createIssue(newIssue: newIssueFormat, completion: completion)
        }
        let model = NewIssueModel(environment: modelEnvironment)
        let newIssueVC = NewIssueViewController(repo: repo, model: model)
        
        newIssueVC.delegate = self
        newIssueVC.coordinator = self
        
        navigationController.pushViewController(newIssueVC, animated: true)
    }
    
    
}

extension NewIssueCoordinator: NewIssueViewControllerDelegate {
    func getCoordinatorType() -> Coordinator.Type {
        return type(of: self)
    }
    
    func goBackToPreviousVC(repo: Repository) {
        self.delegate?.goBackToIssueVC(repo: repo)
    }
}
