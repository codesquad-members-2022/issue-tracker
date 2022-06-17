//
//  SignInViewModel.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/17.
//

import Foundation

protocol SignInViewModelProtocol: AnyObject {
    var buttonAction: ((URL) -> Void)? { get set }
    var errorAction: ((String) -> Void)? { get set }
    var presentAction: (() -> Void)? { get set }
    func requestOAuthCode()
}

final class SignInViewModel: SignInViewModelProtocol {
    private let useCase = SignInManager()
    var buttonAction: ((URL) -> Void)?
    var errorAction: ((String) -> Void)?
    var presentAction: (() -> Void)?

    init() {
        setObserver()
    }

    func requestOAuthCode() {
        useCase.requestCode { [weak self] result in
            switch result {
            case let .success(url):
                self?.buttonAction?(url)
            case let .failure(error):
                self?.errorAction?(error.localizedDescription)
            }
        }
    }
}

// MARK: - Private Method
private extension SignInViewModel {
    func setObserver() {
        NotificationCenter.default.addObserver(forName: SceneDelegate.NotificationNames.didGetSignInError, object: nil, queue: nil) { [weak self] notification in
            self?.errorAction?(notification.description)
            }

        NotificationCenter.default.addObserver(forName: SceneDelegate.NotificationNames.didSignIn, object: nil, queue: nil) { [weak self] _ in
            self?.presentAction?()
        }
    }
}
