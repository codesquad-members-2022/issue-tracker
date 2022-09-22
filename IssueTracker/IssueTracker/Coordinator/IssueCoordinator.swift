//
//  IssueCoordinator.swift
//  IssueTracker
//
//  Created by Bibi on 2022/08/09.
//

import Foundation
import UIKit

protocol IssueCoordinatorDelegate {
    func makeIssue(with repo: Repository)
}

class IssueCoordinator: Coordinator {
    
    var navigationController: UINavigationController
    var viewController: IssueViewController?
    
    var container: Container
    
    var childCoordinators: [Coordinator] = []
    
    var delegate: IssueCoordinatorDelegate?
    
    var selectedRepo: Repository?
    
    init(navigationController: UINavigationController, container: Container, repository: Repository) {
        self.navigationController = navigationController
        self.container = container
        self.selectedRepo = repository
    }
    
    func start() {
        guard let repo = selectedRepo else {
            return
        }
        // MARK: 동적으로 생성되어야 하는 VC는 Container를 사용할 수 없나?
        let model = IssueModel(environment: .init(requestRepositoryIssues: { [weak self] completion in
            self?.container.environment.issueService.requestRepositoryIssues(repo: repo, completion: { result in
                completion(result)
            })
        }))
        let viewcontroller = IssueViewController(model: model, repo: repo)
        viewcontroller.delegate = self
        self.viewController = viewcontroller
        container.register(model)
        container.register(viewcontroller)
    
        loadIssues()
        self.navigationController.pushViewController(viewcontroller, animated: true)
        
    }
    
    func loadIssues() {
        guard let viewController = self.viewController else {
            return
        }
        viewController.loadIssue()
    }

    func fetchIssues() {
        guard let viewController = self.viewController else {
            return
        }
        viewController.fetchIssue()
    }
    
}

extension IssueCoordinator: IssueViewControllerDelegate {
    func touchedNewIssueButton(repo: Repository) {
        self.delegate?.makeIssue(with: repo)
    }
}
