//
//  AppDelegate.swift
//  IssueTracker
//
//  Created by Sujin Jin on 2022/06/13.
//

import UIKit


@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        
        self.window = UIWindow(frame: UIScreen.main.bounds)
        let rootViewController =
        GithubUserDefaults.getToken() != nil
            ? IssueViewController()
            : ViewController()
        window?.rootViewController = rootViewController
        window?.makeKeyAndVisible()
        
        return true
    }
    
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool { // 사파리 -> 콜백으로 들어오는 부분
        print(url)
        if url.absoluteString.starts(with: "issuetracker://login") { // 콜백URL
            if let code = url.absoluteString.split(separator: "=").last.map({ String($0) }) {
                NetworkManager.shared.requestAccessToken(with: code) { result in
                    switch result {
                    case .success(let accessToken):
                        GithubUserDefaults.setToken(uid: accessToken)
                        self.window?.rootViewController = IssueViewController()
                    case .failure(let error):
                        // TODO: 로그인 실패 얼럿띄우기
                        print(error)
                    }
                }
            }
        }
        return true
    }
    
}

