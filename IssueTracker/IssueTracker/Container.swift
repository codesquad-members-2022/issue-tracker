import UIKit

class Container {
    
    private var accessToken: String?
    
    enum Screen {
        case login
        case repos
        case issue(selectedRepo: Repository)
        case newIssue(repo: Repository)
        case optionSelect(option: Option, repo: Repository)
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
            viewController.title = "Issues"
            return viewController
        case .repos:
            let model = ReposModel(service: service)
            model.fetchViewData()
            let viewController = ReposViewController(model: model)
            model.updated = { repos in
                DispatchQueue.main.async {
                    viewController.reloadTableView()
                }
            }
            viewController.title = "Repos"
            return UINavigationController(rootViewController: viewController)
        case .newIssue(let repo):
            let model = NewIssueModel(service: service)
            return NewIssueViewController(repo: repo, model: model)
        case .optionSelect(let option, let repo):
            let model = OptionSelectModel(service: service)
            return OptionSelectViewController(model: model, option: option, repo: repo)
        }
    }
}
