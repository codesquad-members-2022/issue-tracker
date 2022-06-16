//
//  AppDelegate.swift
//  IssueTracker
//
//  Created by Sujin Jin on 2022/06/13.
//

import UIKit
import Alamofire

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    static var accessToken: String? = nil
    
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
                if let dic = json as? [String: String] {
                    AppDelegate.accessToken = dic["access_token"]
                }
            case let .failure(error):
                print(error)
            }
        }
    }
    
    private func requestIssues() {
        let urlString = "https://api.github.com/issues"
//        guard let urlComponents = URLComponents(string: urlString) else {
//            return
//        }
//        urlComponents.queryItems = [ ] // 쿼리 파라미터
        guard let accessToken = AppDelegate.accessToken else {
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

