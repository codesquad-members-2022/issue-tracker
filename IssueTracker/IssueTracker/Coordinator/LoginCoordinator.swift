import Foundation
import UIKit

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

extension LoginCoordinator: LoginViewControllerDelegate {
    func login() {
        self.delegate?.didLoggedIn(coordinator: self)
    }
}
