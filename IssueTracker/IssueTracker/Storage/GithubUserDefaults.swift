import Foundation

struct GithubUserDefaults {

    private static let key = "github_access_token"

    static func setToken(uid: String) {
        UserDefaults.standard.set(uid, forKey: key)
    }

    static func getToken() -> String? {
        return UserDefaults.standard.string(forKey: self.key) ?? nil
    }
}
