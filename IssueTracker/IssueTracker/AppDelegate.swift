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
    let coordinator = AppCoordinator(navigationController: .init())
    let container = Container(environment: .live) // 리팩토링 후 삭제해야!
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // TODO: - 토큰 유효기간 판단
        // 토큰저장할때: 토큰 & 토큰이 저장된 시간 & 유효시간(1일)
        // 토큰이 유효한지 판단
        // UserDefaults.토큰이저장된시간 > 1일
        // { 다시 로그인을 해야된다고 판단 => UerDefaults.token 삭제 }
        
        self.window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = coordinator.buildRootViewController()
        coordinator.start()
        window?.makeKeyAndVisible()
        return true
    }
    
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        // AppDelegate입장에서는 : 콜백으로 들어와서 토큰이 있으면 repoVC, 없으면 loginVC를 rootVC로 설정하면 된다. 나머지 내용은 알 필요가 없다.
        coordinator.fetchToken(url: url)
        let rootVC = coordinator.buildRootViewController()
        self.window?.rootViewController = rootVC
        return true
    }
}
