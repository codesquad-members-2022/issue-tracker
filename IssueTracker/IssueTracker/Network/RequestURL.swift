import Foundation

enum RequestURL: CustomStringConvertible {
    case authorize
    case accessToken
    case issues
    
    var description: String {
        switch self {
        case .authorize:
            return "https://github.com/login/oauth/authorize"
        case .accessToken:
            return "https://github.com/login/oauth/access_token"
        case .issues:
            return "https://api.github.com/issues"
        }
    }
}
