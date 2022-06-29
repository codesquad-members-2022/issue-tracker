import UIKit

struct Container {
    enum Screen {
        case login
        case repos(token: String)
        case issue(token: String, selectedRepo: Repository)
        case newIssue(repo: Repository)
        case optionSelect(token: String, option: Option)
    }

    func buildRootViewController() -> UIViewController {
        if let accessToken = GithubUserDefaults.getToken() {
            return buildViewController(.repos(token: accessToken))
        } else {
            return buildViewController(.login)
        }
    }
    
    func buildViewController(_ screen: Screen) -> UIViewController {
        switch screen {
        case .login:
            return LoginViewController(service: OAuthService())
        case .issue(let token, let selectedRepo):
            let service = IssueService()
            let model = IssueModel(service: service, token: token, repo: selectedRepo)
            let viewController = IssueViewController(model: model, repo: selectedRepo)
            return viewController
        case .repos(let token):
            let viewController = ReposViewController(token: token)
            viewController.title = "Repos"
            return UINavigationController(rootViewController: viewController)
        case .newIssue(let repo):
            return NewIssueViewController(repo: repo)
        case .optionSelect(let token, let option):
            return OptionSelectViewController(token: token, option: option)
        }
    }
}
