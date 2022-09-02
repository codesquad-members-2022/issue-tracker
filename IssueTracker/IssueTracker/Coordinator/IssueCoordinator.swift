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
        let issueModel = IssueModel(environment: .init(requestRepositoryIssues: { [weak self] completion in
            self?.container.environment.issueService.requestRepositoryIssues(repo: repo, completion: { result in
                completion(result)
            })
        }))
        let issueVC = IssueViewController(model: issueModel, repo: repo)
        issueVC.delegate = self
        viewController = issueVC
    
        reloadIssues()
        self.navigationController.pushViewController(issueVC, animated: true)
        
    }

    func reloadIssues() {
        DispatchQueue.main.async { [weak self] in
            self?.viewController?.reloadData()
        }
    }
    
}

extension IssueCoordinator: IssueViewControllerDelegate {
    func touchedNewIssueButton(repo: Repository) {
        self.delegate?.makeIssue(with: repo)
    }
}
