import UIKit

class Container {
    
    let environment: ContainerEnvironment
    private var registeredObjects: [String: Any] = [:]
    private var registeredViewControllerCoordinator: [UIViewController : Coordinator] = [:]
    
    init(environment: ContainerEnvironment) {
        self.environment = environment
    }
    
    // model, viewcontroller, coordinator를 생성 시점에 등록함
    func register<T>(_ object: T) {
        let key = String(describing: T.self) // String(describing: type(of: T.self)) 와의 차이점?? 타입명 vs 타입명.Type
        registeredObjects[key] = object
        if let viewControllerObject = object as? UIViewController {
            registerPair(viewController: viewControllerObject)
        }
    }
    
    // let value: Type = container.resolve() 로 사용
    func resolve<T>() -> T? {
        let key = String(describing: T.self)
        guard let object = registeredObjects[key],
              let object = object as? T else {
            print("⚠️\(key)는 register되지 않음")
            return nil
        }
        return object
    }
    
    func registerPair(viewController: UIViewController) {
        // 받아온 UIViewController타입의 뷰컨을 형변환 또는 타입연산 후, 연관 코디네이터와 함께 저장
        let viewControllerName = String(describing: type(of:viewController))
        
        let allViewController = ViewControllerCoordinator.allCases
        for oneCase in allViewController {
            let oneCaseName = String(describing: oneCase) // Enum case의 이름을 String으로 변환
            if oneCaseName == viewControllerName {
                let coordinatorName = oneCase.rawValue
                guard let coordinator = registeredObjects[coordinatorName],
                      let castedCoordinator = coordinator as? Coordinator else {
                    return
                }
                registeredViewControllerCoordinator[viewController] = castedCoordinator
            }
        }
//
//        switch viewController { // enum을 적용하고 싶음..
//        case is LoginViewController:
//            if let coordinator: LoginCoordinator = resolve(),
//               let typedViewController = viewController as? LoginViewController {
//                registeredViewControllerCoordinator[typedViewController] = coordinator
//            }
//        case is ReposViewController:
//            if let coordinator: ReposCoordinator = resolve(),
//               let typedViewController = viewController as? ReposViewController {
//                registeredViewControllerCoordinator[typedViewController] = coordinator
//            }
//        case is IssueViewController:
//            if let coordinator: IssueCoordinator = resolve(),
//               let typedViewController = viewController as? IssueViewController {
//                registeredViewControllerCoordinator[typedViewController] = coordinator
//            }
//        case is NewIssueViewController:
//            if let coordinator: NewIssueCoordinator = resolve(),
//               let typedViewController = viewController as? NewIssueViewController {
//                registeredViewControllerCoordinator[typedViewController] = coordinator
//            }
//        case is OptionSelectViewController:
//            if let coordinator: OptionSelectCoordinator = resolve(),
//               let typedViewController = viewController as? OptionSelectViewController {
//                registeredViewControllerCoordinator[typedViewController] = coordinator
//            }
//        default:
//            return
//        }
    }
    
    func resolvePair(of viewController: UIViewController) -> Coordinator? {
        return registeredViewControllerCoordinator[viewController]
    }
    
//    private func registerLoginModel() {
//        let loginModel = LoginModel(environment: .init(requestCode: { [weak self] completion in
//            self?.environment.oAuthService.requestCode(completion: { result in
//                completion(result)
//            })
//        }))
//        register(loginModel)
//    }
//
//    private func registerLoginViewController() {
//        guard let loginModel: LoginModel = self.resolve() else {
//            self.registerLoginModel()
//            return
//        }
//        let loginVC = LoginViewController(model: loginModel)
//        register(loginVC)
//    }
//
//    private func registerReposModel() {
//        let reposModel = ReposModel(environment: .init(requestRepos: { [weak self] completion in
//            self?.environment.issueService.requestRepos(completion: { result in
//                completion(result)
//            })
//        }))
//        register(reposModel)
//    }
//
//    private func registerReposViewController() {
//        guard let reposModel: ReposModel = resolve() else {
//            self.registerReposModel()
//            return
//        }
//        let reposVC = ReposViewController(model: reposModel)
//        register(reposVC)
//    }
    
    func fetchAccessToken(url: URL, completion: @escaping (Bool) -> Void) {
        environment.oAuthService.fetchToken(from: url) { [weak self] accessToken in
            guard let token = accessToken,
                  let self = self else {
                completion(false)
                return
            }
            self.environment.githubUserDefaults.setToken(token)
            self.environment.issueService.setAccessToken(token)
            completion(true)
        }
    }
}

// Container에 필요한 Environment : 필요한 의존성을 가진 객체
struct ContainerEnvironment {
    var githubUserDefaults: GithubUserDefaults
    var oAuthService: OAuthService
    var issueService: IssueService
    
    static let live: ContainerEnvironment = {
        let githubUserDefaults = GithubUserDefaults()
        let token = githubUserDefaults.getToken()
        
        // 문제 발생 지점 -
        return ContainerEnvironment(githubUserDefaults: githubUserDefaults, oAuthService: OAuthService(), issueService: IssueService(token: token ?? ""))
    }()
}
