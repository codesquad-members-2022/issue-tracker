import Foundation
import Alamofire

typealias UserToken = String

enum GitHubError: Error {
    case issueNotFound
    case cannotCreateIssue
}
