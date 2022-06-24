//
//  RootWindowViewModel.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/23.
//

import Foundation

final class RootWindowViewModel {

    let issueListViewModel = IssueListViewModel()
    let loginRepository = LoginRepository()
//    var loginStatus: Bool = false {
//        didSet {
//            self.onUpdateLoginSatus()
//
//        }
//    }

     // status
    var onUpdateLoginSatus: (Bool) -> Void = { _ in  }

//    // 상태 알림 업데이트
//    func isValidLogin(completion: @escaping (Bool) -> Void) {
//        // TODO: 깃헙 UserApi를 통해서 로그인 이 되어있는지 판단하는 로직 구현
//    }

    // Action
    func requestLoginStatus() {
        guard let token = UserDefaults.standard.object(forKey: Environment.token) as? String else { self.onUpdateLoginSatus(false)
            return }
        loginRepository.requestLoginStatus(IssueTrackerTarget.requestLoginStatus(token: token)) { [weak self] answer in
            self?.onUpdateLoginSatus(answer)
        }

    }
}
