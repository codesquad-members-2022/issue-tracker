//
//  AppFlowCoordinator.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/14.
//

import UIKit

protocol Coordinator {
    func start()
}

final class AppFlowCoordinator: Coordinator {
    private let navigationController: UINavigationController

    init(navigationController: UINavigationController) {
        self.navigationController = navigationController
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    func start() {
        startFlow()
    }

    func startFlow() {
        let flow = LoginFlowCoordinator(navigationController: navigationController)
        flow.start()
    }
}
