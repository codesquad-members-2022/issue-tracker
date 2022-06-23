//
//  AppFlowDIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/17.
//

import Foundation

enum LocalStorageConstants {
    static let AuthCode = "AuthorizationCode"
}

final class AppFlowDIContainer {
    private let localStorage = UserDefaults.standard

    func makeLoginFlowDIContainer() -> DIContainer {
        LoginFlowDIContainer()
    }
}

extension AppFlowDIContainer: AppFlowCoordinatorDependencies {
    func checkUserLoggedIn() -> Bool {
        localStorage.string(forKey: LocalStorageConstants.AuthCode) != nil
    }
}
