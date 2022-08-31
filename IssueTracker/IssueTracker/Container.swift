import UIKit

class Container {
    //   - 인스턴스를 소유하고 관리하는 역할 - 객체 생성 및 의존성 주입
    //    - MVVM 생성
    //    - 사용할 객체를 Container에 등록
    //    - 객체를 사용할 때는 Continer에 요청
    
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
        let loginModel = LoginModel(environment: .init(requestCode: { [weak self] completion in // 모델에 필요한 service의 클로저만 넣어주기
            self?.environment.oAuthService.requestCode(completion: { result in
                completion(result)
            })
        }))
        register(loginModel)
    }
    
    // MARK: Container에서 register하는 요소들 간에 종속관계가 생기는 경우 어떻게 register해야..??
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
    
    // MARK: 뷰모델 초기화에 유저 선택 데이터가 필요한 경우 초기화방법/시점은..?
    // 이슈모델은 한번 만들어 두고 계속 쓰는 게 아니라 selectedRepo가 달라질때마다 새로 만들어야 하는데.. 그럼 컨테이너로 관리하기 적당하지 않은 대상인가??
    // 필요할때 초기화하고 써도 되나? 근데 그러면 컨테이너가 미리 만들어두는 의미가..있나?
    // -> 뷰만 재사용하고 데이터는 repo를 새로 선택할때마다 업데이트.
    private func registerIssueModel(selectedRepo: Repository) {
    }

    private func registerIssueViewController() {
        
    }
    
    // MARK: (구) VC생성 메서드
    func buildViewController(_ screen: Screen) -> UIViewController {
        switch screen {
        case .login:
            let model = LoginModel(environment: .init(requestCode: { [weak self] completion in
                self?.environment.oAuthService.requestCode { result in
                    completion(result)
                }
            }))
            return LoginViewController(model: model)
        case .repos:
            // Repost에 필요한 service조각만 Model에 넣어주기(클로저 방식 사용)
            // ReposModelEnvironment로 IssueService의 requestRepos()를 넣어줘야 한다 - completion으로 넘겨줌
            // 클로저 사용 시 weak 사용 반드시 확인하기!!
            let model = ReposModel(environment: .init(requestRepos: { [weak self] completion in
                self?.environment.issueService.requestRepos(completion: { result in
                    completion(result)
                })
            }))
            let viewController = ReposViewController(model: model)
            // VC.viewDidLoad()에서 하던 준비작업을 여기서 함
            model.fetchViewData()
            // vc는 모델을 가지는데, 모델은 vc의 테이블뷰를 참조해야 하므로 순환참조를 방지하기 위해 weak vc로 선언
            model.updated = { [weak viewController] repos in
                DispatchQueue.main.async {
                    viewController?.reloadTableView()
                }
            }
            viewController.title = "Repos"
            return UINavigationController(rootViewController: viewController)
        case .issue(let selectedRepo):
            let model = IssueModel(
                environment: .init(requestRepositoryIssues: { [weak self] completion in
                    self?.environment.issueService.requestRepositoryIssues(repo: selectedRepo, completion: { result in
                        completion(result)
                    })
                })
            )
            let viewController = IssueViewController(model: model, repo: selectedRepo)
            model.requestIssue()
            model.updatedIssues = {
                DispatchQueue.main.async { [weak viewController] in
                    viewController?.reloadData()
                }
            }
            viewController.title = "Issues"
            return viewController
        case .newIssue(let repo):
            // MARK: 아래와 같이 파라미터와 completion이 함께 필요한 클로저라면, 클로저를 통해 넘기지 않고 직접 참조해서 넣어줘도 괜찮은지?? (weak self 필요없음) -> 아님 메모리누수 나고있음~
            let newIssueModelEnvironment = NewIssueModelEnvironment { [weak self] newIssueFormat, completion in
                self?.environment.issueService.createIssue(newIssue: newIssueFormat, completion: completion)
            }
//            let model = NewIssueModel(environment: .init(createIssue: environment.issueService.createIssue(title:repo:content:label:milestone:assignee:completion:)))
            let model = NewIssueModel(environment: newIssueModelEnvironment)
            return NewIssueViewController(repo: repo, model: model)
        case .optionSelect(let option, let repo):
            let model = OptionSelectModel(environment:
                    .init(requestRepositoryLabels:
                            environment.issueService.requestRepositoryLabels(repo:completion:),
                          requestRepositoryMilestones:
                            environment.issueService.requestRepositoryMilestones(repo:completion:),
                          requestRepositoryAssigness:
                            environment.issueService.requestRepositoryAssigness(repo:completion:)))
            model.requestOptions(option, repo: repo)
            let viewController = OptionSelectViewController(model: model, option: option, repo: repo)
            model.updatedOptions = {
                DispatchQueue.main.async { [weak viewController] in
                    viewController?.reloadData()
                }
            }
            return viewController
        }
    }
    
    func fetchAccessToken(url: URL) {
        environment.oAuthService.fetchToken(from: url) { [weak self] accessToken in
            guard let token = accessToken,
                  let self = self else {
                // TODO: 로그인 실패 얼럿띄우기
                return // 옵셔널 처리 시 리턴값이 애매해질 때 한가지 방법은, 리턴값 대신 completion(리턴값) -> Void 를 사용하는 것
            }
            self.environment.githubUserDefaults.setToken(token)
        }
    }
    
    func buildRootViewController() -> UIViewController {
        self.environment.githubUserDefaults.getToken() != nil
        ? self.buildViewController(.repos)
        : self.buildViewController(.login)
    }
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
