import UIKit

class Container {
    
    private var accessToken: String?
    
    enum Screen {
        case login
        case repos
        case issue(selectedRepo: Repository)
        case newIssue(repo: Repository)
        case optionSelect(option: Option)
    }
    
    init(token: String?) {
        self.accessToken = token
    }

    func setToken(_ token: String) {
        accessToken = token
    }
    
    func buildRootViewController() -> UIViewController {
        accessToken != nil ? buildViewController(.repos) : buildViewController(.login)
    }
    
    func buildViewController(_ screen: Screen) -> UIViewController {
        let service = IssueService(token: self.accessToken ?? "")
        switch screen {
        case .login:
            return LoginViewController(service: OAuthService())
        case .issue(let selectedRepo):
            let model = IssueModel(service: service, repo: selectedRepo)
            let viewController = IssueViewController(model: model, repo: selectedRepo)
            viewController.title = "이슈들"
            return viewController
        case .repos:
            let model = ReposModel(service: service)
            let viewController = ReposViewController(model: model)
            viewController.title = "Repos"
            return UINavigationController(rootViewController: viewController)
        case .newIssue(let repo):
            let model = NewIssueModel(service: service)
            return NewIssueViewController(repo: repo, model: model)
        case .optionSelect(let option):
            return OptionSelectViewController(service: service, option: option)
        }
    }
}
