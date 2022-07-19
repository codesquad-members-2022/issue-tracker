import UIKit

class Container {
    
    let environment: ContainerEnvironment
    
    init(environment: ContainerEnvironment) {
        self.environment = environment
    }
    
    func buildViewController(_ screen: Screen) -> UIViewController {
        let service = environment.issueService
        switch screen {
        case .login:
            return LoginViewController(service: environment.oAuthService)
        case .issue(let selectedRepo): // 필요한 service조각만 넣어주기(클로저?)
            let model = IssueModel(service: service, repo: selectedRepo)
            let viewController = IssueViewController(model: model, repo: selectedRepo) // Issue -> Issues
            return viewController
        case .repos:
            let model = ReposModel(service: service)
            let viewController = ReposViewController(model: model)
            return UINavigationController(rootViewController: viewController)
        case .newIssue(let repo):
            let model = NewIssueModel(service: service)
            return NewIssueViewController(repo: repo, model: model)
        case .optionSelect(let option, let repo):
            let model = OptionSelectModel(service: service)
            return OptionSelectViewController(model: model, option: option, repo: repo)
        }
    }
    
    func checkRootViewController(url: URL, completion: @escaping (UIViewController) -> Void) {
        // TODO: 분리하는중
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
