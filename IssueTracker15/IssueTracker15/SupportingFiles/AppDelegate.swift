//
//  AppDelegate.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/14.
//

import UIKit

@main
class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        registUseCaseDependencies()
        return true
    }
    
    // UseCase 등록
    func registUseCaseDependencies() {
        UseCaseContainer.shared.regist(type: GithubLoginUseCase.self) {
            return GithubLoginUseCase(model: GitHubLoginModel())
        }
//        UseCaseContainer.shard.regist(instance: AppleLoginUseCase(model: AppleLoginModel()))
    }
}

