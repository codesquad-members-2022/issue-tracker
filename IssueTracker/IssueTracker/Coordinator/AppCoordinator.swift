//
//  AppCoordinator.swift
//  IssueTracker
//
//  Created by Bibi on 2022/08/09.
//

import Foundation
import UIKit

class AppCoordinator: Coordinator {
//    - 모든 ViewController를 모으고 관리하는 역할 - flow logic 관리
//    - 코디네이터는 Container만 알고 있으면 된다. MVVM에 대해 알 필요 없음
//    - flow logic을 VC로부터 분리
//    - VC 재사용 가능
//    - VC와 코디네이터는 1:1 관계
    
    let container = Container(environment: .live)
    
    private let navigationController: UINavigationController
    
    var childCoordinators: [Coordinator] = []
    
    // var isLoggedIn = false
    
    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
    }
    
    func start() {
        // entrypoint에서 보일 화면 로직 설정
        container.environment.githubUserDefaults.getToken() != nil
        ? showReposViewController()
        : showLoginViewController()
    }
    
    func fetchToken(url: URL) {
        container.fetchAccessToken(url: url)
    }
    
    func buildRootViewController() -> UIViewController {
        container.environment.githubUserDefaults.getToken() != nil
        ? container.buildViewController(.repos)
        : container.buildViewController(.login)
    }
    
    // 아래에 필요한 뷰컨 초기화를 맡는 메서드 로직 작성
    private func showLoginViewController() {
        let coordinator = LoginCoordinator(navigationController: navigationController, container: container)
        coordinator.delegate = self
        coordinator.start()
        self.childCoordinators.append(coordinator)
    }
    
    private func showReposViewController() {
        let coordinator = ReposCoordinator(navigationController: navigationController, container: container)
        coordinator.delegate = self
        coordinator.start()
        self.childCoordinators.append(coordinator)
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
        // showIssueViewController()
        print("IssueVC를 보여줌")
    }
}
