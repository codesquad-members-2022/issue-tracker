import UIKit

class Container {
    
    let environment: ContainerEnvironment
    private var objects: [String: Any] = [:]
    //
    
    init(environment: ContainerEnvironment) {
        self.environment = environment
        registerObjects()
    }
    
    func registerObjects() {
        registerLoginModel()
        registerLoginViewController()
        registerReposModel()
        registerReposViewController()
    }
    
    // 외부 등록 허용??
    func register<T>(_ object: T) {
        let key = String(describing: type(of: T.self)) // 해당 클래스의 이름을 key값으로 저장
        objects[key] = object
    }
    
    // let value: Type = container.resolve() 로 사용
    func resolve<T>() -> T? {
        let key = String(describing: type(of: T.self))
        guard let object = objects[key],
              let object = object as? T else {
            print("\(key)는 register되지 않음")
            return nil
        }
        return object
    }
    
    private func registerLoginModel() {
        let loginModel = LoginModel(environment: .init(requestCode: { [weak self] completion in
            self?.environment.oAuthService.requestCode(completion: { result in
                completion(result)
            })
        }))
        register(loginModel)
    }
    
    // MARK: Container에서 register하는 요소들 간에 종속관계가 생기는 경우 어떻게 register해야??
    private func registerLoginViewController() {
        guard let loginModel: LoginModel = self.resolve() else {
            self.registerLoginModel()
            return
        }
        let loginVC = LoginViewController(model: loginModel)
        register(loginVC)
    }
    
    private func registerReposModel() {
        let reposModel = ReposModel(environment: .init(requestRepos: { [weak self] completion in
            self?.environment.issueService.requestRepos(completion: { result in
                completion(result)
            })
        }))
        register(reposModel)
    }
    
    private func registerReposViewController() {
        guard let reposModel: ReposModel = resolve() else {
            self.registerReposModel()
            return
        }
        let reposVC = ReposViewController(model: reposModel)
        register(reposVC)
    }
    
    func fetchAccessToken(url: URL) {
        environment.oAuthService.fetchToken(from: url) { [weak self] accessToken in
            guard let token = accessToken,
                  let self = self else {
                return
            }
            self.environment.githubUserDefaults.setToken(token)
        }
    }
    
//    func buildRootViewController() -> UIViewController {
//        self.environment.githubUserDefaults.getToken() != nil
//        ? self.buildViewController(.repos)
//        : self.buildViewController(.login)
//    }
}

extension Container {
    enum Screen {
        case login
        case repos
        case issue(selectedRepo: Repository)
        case newIssue(repo: Repository)
        case optionSelect(option: Option, repo: Repository)
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
        
        return ContainerEnvironment(githubUserDefaults: githubUserDefaults, oAuthService: OAuthService(), issueService: IssueService(token: token ?? ""))
    }()
}
