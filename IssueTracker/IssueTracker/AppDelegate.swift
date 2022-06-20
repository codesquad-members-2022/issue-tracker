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
        
        // TODO: - 토큰 유효기간 판단
        // 토큰저장할때: 토큰 & 토큰이 저장된 시간 & 유효시간(1일)
        // 토큰이 유효한지 판단
        // UserDefaults.토큰이저장된시간 > 1일
        // { 다시 로그인을 해야된다고 판단 => UerDefaults.token 삭제 }
        
        self.window = UIWindow(frame: UIScreen.main.bounds)
        let rootViewController =
        GithubUserDefaults.getToken() != nil
            ? UINavigationController(rootViewController: IssueViewController())
            : ViewController()
        window?.rootViewController = rootViewController
        window?.makeKeyAndVisible()
        
        return true
    }
    
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        if url.absoluteString.starts(with: "issuetracker://login") { // 콜백URL
            if let code = url.absoluteString.split(separator: "=").last.map({ String($0) }) {
                NetworkManager.shared.requestAccessToken(with: code) { result in
                    switch result {
                    case .success(let accessToken):
                        GithubUserDefaults.setToken(uid: accessToken)
                        self.window?.rootViewController =
                        UINavigationController(rootViewController: IssueViewController())
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

