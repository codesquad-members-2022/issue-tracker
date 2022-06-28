//
//  RootWindow.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/23.
//

import Foundation
import UIKit

final class RootWindow: UIWindow {

    let rootWindowViewModel = RootWindowViewModel()

    init(scene: UIWindowScene) {
        super.init(windowScene: scene)
        overrideUserInterfaceStyle = .light
    }

    func setRootViewController() {
        rootWindowViewModel.onUpdateLoginSatus = { answer in
            if answer {
                DispatchQueue.main.async {
                    let issueListVC = IssueListViewController(viewModel: IssueListViewModel())
                    issueListVC.viewModel.loadIssueList()
                    self.rootViewController = issueListVC
                    return
                }
            } else {
                DispatchQueue.main.async {
                    self.rootViewController = LoginViewController(viewModel: LoginViewModel(repository: LoginRepository()))
                    return
                }
            }
        }
        rootWindowViewModel.requestLoginStatus()
    }

    func setIssueListVC() {
        DispatchQueue.main.async {
            let issueListVC = IssueListViewController(viewModel: IssueListViewModel())
            issueListVC.viewModel.loadIssueList()
            self.rootViewController = issueListVC
            return
        }
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

}
