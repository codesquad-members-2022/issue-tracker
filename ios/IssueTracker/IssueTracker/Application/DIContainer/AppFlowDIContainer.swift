//
//  AppFlowDIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/17.
//

import Foundation

enum LocalStorageConstants {
    static let AuthCode = "AuthorizationCode"
    static let AuthToken = "AccessToken"
}

final class AppFlowDIContainer {
    private let localStorage = UserDefaults.standard
}

extension AppFlowDIContainer: AppFlowCoordinatorDependencies {
    func checkUserLoggedIn() -> Bool {
        localStorage.string(forKey: LocalStorageConstants.AuthToken) != nil
    }

    func makeLoginFlowDIContainer() -> DIContainer {
        LoginFlowDIContainer()
    }

    func makeTabBarFlowDIContainer() -> DIContainer {
        TabBarFlowDIContainer()
    }
}
