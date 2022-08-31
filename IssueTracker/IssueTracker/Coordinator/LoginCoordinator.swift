import Foundation
import UIKit

// 이 코디네이터에서 AppCoordinator에게 위임할 작업 (화면전환 등)
protocol LoginCoordinatorDelegate {
    func didLoggedIn(coordinator: LoginCoordinator)
}

class LoginCoordinator: Coordinator {
    
    let navigationController: UINavigationController
    
    let container: Container
    var childCoordinators: [Coordinator] = []
    var delegate: LoginCoordinatorDelegate?
    
    init(navigationController: UINavigationController, container: Container) {
        self.navigationController = navigationController
        self.container = container
    }
    
    func start() { // LoginVC 초기화 코드
        guard let loginVC: LoginViewController = container.resolve() else {
            return
        }
        loginVC.delegate = self
        
        navigationController.pushViewController(loginVC, animated: false)
    }
}

// 로그인코디네이터가 로그인뷰컨을 위해 대신 해줄 동작 정의
// 코디네이터이므로 뷰컨전환 로직을 맡는다
// 이때 로그인코디네이터는 하위 코디네이터이고, 상위 코디네이터가 존재하므로 상위 코디네이터에게 이를 또다시 위임함 - LoginCoordinatorDelegate
extension LoginCoordinator: LoginViewControllerDelegate {
    func login() {
        self.delegate?.didLoggedIn(coordinator: self)
    }
}
