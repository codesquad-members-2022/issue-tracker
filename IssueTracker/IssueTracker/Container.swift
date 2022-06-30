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
            return viewController
        case .repos:
            let viewController = ReposViewController(service: service)
            viewController.title = "Repos"
            return UINavigationController(rootViewController: viewController)
        case .newIssue(let repo):
            return NewIssueViewController(repo: repo, service: service)
        case .optionSelect(let option):
            return OptionSelectViewController(service: service, option: option)
        }
    }
}
