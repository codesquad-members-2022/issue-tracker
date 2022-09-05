//
//  AppCoordinator.swift
//  IssueTracker
//
//  Created by Bibi on 2022/08/09.
//

import Foundation
import UIKit

class AppCoordinator: NSObject, Coordinator {
//    - 모든 ViewController를 모으고 관리하는 역할 - flow logic 관리
//    - 코디네이터는 Container만 알고 있으면 된다. MVVM에 대해 알 필요 없음
//    - flow logic을 VC로부터 분리
//    - VC 재사용 가능
//    - VC와 코디네이터는 1:1 관계
    
    let container = Container(environment: .live)
    
    let navigationController: UINavigationController
    
    var childCoordinators: [Coordinator] = []
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
    }
    
    func start() {
        
        // navigationController의 동작 감지
        navigationController.delegate = self
        
        // entrypoint에서 보일 화면 로직 설정
        container.environment.githubUserDefaults.getToken() != nil
        ? showReposViewController()
        : showLoginViewController()
    }
    
    func fetchToken(url: URL) {
        container.fetchAccessToken(url: url)
    }
    
    func buildRootViewController() -> UIViewController {
        var vc = UIViewController()
        if container.environment.githubUserDefaults.getToken() != nil {
            if let reposVC: ReposViewController = container.resolve() {
                vc = reposVC
            }
        } else {
            if let loginVC: LoginViewController = container.resolve() {
                vc = loginVC
            }
        }
        return vc
    }
    
    private func showLoginViewController() {
        let coordinator = LoginCoordinator(navigationController: navigationController, container: container)
        coordinator.delegate = self
        container.register(coordinator)
        coordinator.start()
        self.childCoordinators.append(coordinator)
    }
    
    private func showReposViewController() {
        let coordinator = ReposCoordinator(navigationController: navigationController, container: container)
        coordinator.delegate = self
        container.register(coordinator)
        coordinator.start()
        self.childCoordinators.append(coordinator)
    }
    
    private func showIssueViewController(repo: Repository) {
        let coordinator = IssueCoordinator(navigationController: navigationController, container: container, repository: repo)
        coordinator.delegate = self
        container.register(coordinator)
        coordinator.start()
        self.childCoordinators.append(coordinator)
    }
    
    private func showNewIssueViewController(repo: Repository) {
        let coordinator = NewIssueCoordinator(navigationController: navigationController, container: container, repo: repo)
        coordinator.delegate = self
        container.register(coordinator)
        coordinator.start()
        self.childCoordinators.append(coordinator)
    }
    
    private func showOptionSelectViewController(option: Option, repo: Repository) {
        let coordinator = OptionSelectCoordinator(navigationController: navigationController, container: container, option: option, repo: repo)
        coordinator.delegate = self
        container.register(coordinator)
        coordinator.start()
        self.childCoordinators.append(coordinator)
    }
    
    private func removeChildCoordinator(child: Coordinator) {
        for (index, coordinator) in childCoordinators.enumerated() {
            if coordinator === child {
                childCoordinators.remove(at: index)
            }
        }
    }
}

// 아래에 delegate받아 처리할 메서드(화면전환) 로직 작성
extension AppCoordinator: LoginCoordinatorDelegate {
    func didLoggedIn(coordinator: LoginCoordinator) {
        childCoordinators = childCoordinators.filter { $0 !== coordinator } // 자식 코디네이터들에서 LoginCoordinator 삭제
        showReposViewController()
    }
}

extension AppCoordinator: ReposCoordinatorDelegate {
    func didSelect(repository: Repository) {
        showIssueViewController(repo: repository)
    }
}

extension AppCoordinator: IssueCoordinatorDelegate {
    func makeIssue(with repo: Repository) {
        showNewIssueViewController(repo: repo)
    }
}

extension AppCoordinator: NewIssueCoordinatorDelegate {
    func showOptions(option: Option, repo: Repository) {
        showOptionSelectViewController(option: option, repo: repo)
    }
    
    func goBackToIssueVC(repo: Repository, title: String) {
        // 기존 스택에서 IssueVC삭제 or 이전 화면으로 되돌아옴
        DispatchQueue.main.async { [weak self] in
            self?.navigationController.popViewController(animated: true)
        // TODO: 여전히 이전 뷰로 되돌아오면서 새로 생긴 issue를 갱신해 보여주는 동작이 되지 않음 (goBackToNewIssueVC도 마찬가지)
        }
        
        guard let issueCoordinator: IssueCoordinator = container.resolve() else {
            return
        }
        //request
        issueCoordinator.fetchIssues(title: title)
//        issueCoordinator.reloadIssues()
    }
}

extension AppCoordinator: OptionSelectCoordinatorDelegate {
    func goBackToNewIssueVC(item: Optionable, option: Option) {
        DispatchQueue.main.async {
            self.navigationController.popViewController(animated: true)
        }
        
        guard let newIssueCoordinator: NewIssueCoordinator = container.resolve() else {
            return
        }
        newIssueCoordinator.setOptions(item: item, option: option)
        newIssueCoordinator.reloadOptions()
    }
    
    
}

extension AppCoordinator: UINavigationControllerDelegate {
    func navigationController(_ navigationController: UINavigationController, didShow viewController: UIViewController, animated: Bool) { // navCon이 새 뷰컨을 보여준 직후 호출됨
        // 어떤 뷰컨으로부터 이동했는지 확인
        // MARK: 뭐하는 코드인지 공부하기
        guard let fromViewController = navigationController.transitionCoordinator?.viewController(forKey: .from) else {
            return
        }
        
        // navStack에 존재한다면 - pop이 아닌 push
        if navigationController.viewControllers.contains(fromViewController) {
            return
        }
        
        // navStack에 존재하지 않음 = pop됨 : 해당 뷰컨의 coordinator를 childCoordinators에서 지워야 함
        // MARK: 모든 뷰컨의 코디네이터 관리하면서도 코드중복 피하는 방법..?
        if fromViewController as? LoginViewController != nil,
            let coordinator: LoginCoordinator = container.resolve() {
            removeChildCoordinator(child: coordinator)
        }
        if fromViewController as? ReposViewController != nil,
            let coordinator: ReposCoordinator = container.resolve() {
            removeChildCoordinator(child: coordinator)
        }
        if fromViewController as? IssueViewController != nil,
            let coordinator: IssueCoordinator = container.resolve() {
            removeChildCoordinator(child: coordinator)
        }
        if fromViewController as? NewIssueViewController != nil,
            let coordinator: NewIssueCoordinator = container.resolve() {
            removeChildCoordinator(child: coordinator)
        }
        if fromViewController as? OptionSelectViewController != nil,
            let coordinator: OptionSelectCoordinator = container.resolve() {
            removeChildCoordinator(child: coordinator)
        }
    }
}
