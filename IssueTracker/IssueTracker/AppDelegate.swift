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
    var coordinator: AppCoordinator?
    let container = Container(environment: .live) // 리팩토링 후 삭제해야!
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        let navController = UINavigationController()
        
        coordinator = AppCoordinator(navigationController: navController)
        
        coordinator?.start()
         
        self.window = UIWindow(frame: UIScreen.main.bounds)
//        window?.rootViewController = coordinator?.buildRootViewController()
        // TODO: 왜 위 코드가 아닌 아래 코드가 되는 건지 확인.. nav
        window?.rootViewController = navController
        window?.makeKeyAndVisible()
        return true
    }
    
    func application(_ app: UIApplication, open url: URL, options: [UIApplication.OpenURLOptionsKey : Any] = [:]) -> Bool {
        coordinator?.fetchToken(url: url)
        let rootVC = coordinator?.buildRootViewController()
        self.window?.rootViewController = rootVC
        return true
    }
}
