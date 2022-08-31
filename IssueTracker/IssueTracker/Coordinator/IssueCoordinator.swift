//
//  IssueCoordinator.swift
//  IssueTracker
//
//  Created by Bibi on 2022/08/09.
//

import Foundation
import UIKit

protocol IssueCoordinatorDelegate {
    func makeIssue()
}

class IssueCoordinator: Coordinator {
    
    var navigationController: UINavigationController
    
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
        issueVC.reloadData()
        
        print(navigationController.viewControllers)
        self.navigationController.viewControllers = [issueVC]
        // MARK: 아래, 위 중 하나만 사용해 화면 전환 - 무슨 차이?
        // 위는 스택에 1개의 뷰컨만 존재하게 하므로 back버튼이 생기지 않음
//        navigationController.pushViewController(issueVC, animated: true) // 오류
        print(navigationController.viewControllers)
        // TODO: IssueVC의 viewDidLoad가 호출이 안됨..!!
    }

}

extension IssueCoordinator: IssueViewControllerDelegate {
    func touchedNewIssueButton() {
        self.delegate?.makeIssue()
    }
}
