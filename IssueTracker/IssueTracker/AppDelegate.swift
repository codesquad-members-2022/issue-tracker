//
//  AppDelegate.swift
//  IssueTracker
//
//  Created by Sujin Jin on 2022/06/13.
//

import UIKit
import Alamofire

struct GithubUserDefaults {

    private static let key = "github_access_token"

    static func setToken(uid: String) {
        UserDefaults.standard.set(uid, forKey: key)
    }

    static func getToken() -> String? {
        return UserDefaults.standard.string(forKey: self.key) ?? nil
    }
}


@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        
        self.window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = ViewController()
        window?.makeKeyAndVisible()
        
        return true
    }
    
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool { // 사파리 -> 콜백으로 들어오는 부분
        print(url)
        if url.absoluteString.starts(with: "issuetracker://login") { // 콜백URL
            if let code = url.absoluteString.split(separator: "=").last.map({ String($0) }) {
                requestAccessToken(with: code)
                requestIssues()
            }
        }
       
        return true
    }
    
    private func requestAccessToken(with code: String) {
        let url = "https://github.com/login/oauth/access_token"
        
        let parameters = ["client_id": "\(PrivateStorage.clientId)",
                          "client_secret": "\(PrivateStorage.clientSecret)",
                          "code": code]
        let headers: HTTPHeaders = ["Accept": "application/json"]
        
        AF.request(url, method: .post, parameters: parameters, headers: headers).responseDecodable(of: [String: String].self) { (response) in
            switch response.result {
            case let .success(json):
                if let dic = json as? [String: String],
                   let accessToken = dic["access_token"] {
                    GithubUserDefaults.setToken(uid: accessToken)
                }
            case let .failure(error):
                print(error)
            }
        }
    }
    
    private func requestIssues() {
        let urlString = "https://api.github.com/issues"

        guard let accessToken = GithubUserDefaults.getToken() else {
            return
        }
        print("accessToken : \(accessToken)")
        let headers: HTTPHeaders = [
            "Accept": "application/vnd.github.v3+json",
            "Authorization": "token \(accessToken)"
        ]
        AF.request(urlString, method: .get, headers: headers)
            .responseDecodable(of: [Issue].self) { (response) in
                print("response: \(response)")
            switch response.result {
            case let .success(json):
                print(json)
            case let .failure(error):
                print(error)
            }
        }
        
    }
}

