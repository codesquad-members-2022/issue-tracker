import UIKit

class Container {
    // VC를 찍어내는 역할
    
    let environment: ContainerEnvironment
    
    init(environment: ContainerEnvironment) {
        self.environment = environment
    }
    
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
            // 클로저 사용 시 weak 사용 염두에 두기!!
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
            // MARK: 아래와 같이 파라미터와 completion이 함께 필요한 클로저라면, 클로저를 통해 넘기지 않고 직접 참조해서 넣어줘도 괜찮은지?? (weak self 필요없음)
            let model = NewIssueModel(environment: .init(createIssue: environment.issueService.createIssue(title:repo:content:label:milestone:assignee:completion:)))
            return NewIssueViewController(repo: repo, model: model)
        case .optionSelect(let option, let repo):
            let model = OptionSelectModel(environment:
                    .init(requestRepositoryLabels:
                            environment.issueService.requestRepositoryLabels(repo:completion:),
                          requestRepositoryMilestones:
                            environment.issueService.requestRepositoryMilestones(repo:completion:),
                          requestRepositoryAssigness:
                            environment.issueService.requestRepositoryAssigness(repo:completion:)))
            return OptionSelectViewController(model: model, option: option, repo: repo)
        }
    }
    
    func checkRootViewController(url: URL, completion: @escaping (UIViewController) -> Void) {
        environment.oAuthService.fetchToken(from: url) { [weak self] accessToken in
            guard let token = accessToken,
                  let self = self else {
                // TODO: 로그인 실패 얼럿띄우기
                return // 옵셔널 처리 시 리턴값이 애매해질 때 한가지 방법은, 리턴값 대신 completion(리턴값) -> Void 를 사용하는 것
            }
            self.environment.githubUserDefaults.setToken(token)
            completion(self.buildRootViewController())
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
