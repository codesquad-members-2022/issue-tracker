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
        window?.rootViewController = ViewController()
        window?.makeKeyAndVisible()
        
        return true
    }
    
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool { // 사파리 -> 콜백으로 들어오는 부분
        print(url)
        if url.absoluteString.starts(with: "issuetracker://login") { // 콜백URL
            if let code = url.absoluteString.split(separator: "=").last.map({ String($0) }) {
                NetworkManager.shared.requestAccessToken(with: code)
                window?.rootViewController = IssueViewController()
            }
        }
        return true
    }
    
}

