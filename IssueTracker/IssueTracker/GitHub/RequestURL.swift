import Foundation

enum RequestURL: CustomStringConvertible {
    case authorize
    case accessToken
    case issues
    case createIssue(owner: String, repo: String)
    
    var description: String {
        switch self {
        case .authorize:
            return "https://github.com/login/oauth/authorize"
        case .accessToken:
            return "https://github.com/login/oauth/access_token"
        case .issues:
            return "https://api.github.com/issues"
        case let .createIssue(owner, repo):
            return "https://api.github.com/repos/\(owner)/\(repo)/issues"
        }
    }
}
