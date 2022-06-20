//
//  AppFlowDIContainer.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/17.
//

import Foundation

final class AppFlowDIContainer {
    func makeLoginFlowDIContainer() -> DIContainer {
        LoginFlowDIContainer()
    }
}

extension AppFlowDIContainer: AppFlowCoordinatorDependencies {}
