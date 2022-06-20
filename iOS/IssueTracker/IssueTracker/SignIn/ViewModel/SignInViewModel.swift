//
//  SignInViewModel.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/17.
//

import Foundation

protocol SignInViewModelProtocol: AnyObject {
    var buttonAction: Observable<URL?> { get }
    var errorAction: Observable<String> { get }
    var presentAction: Observable<Void> { get }
    func requestOAuthCode()
}

final class SignInViewModel: SignInViewModelProtocol {
    private let useCase = SignInManager()
    private(set) var buttonAction: Observable<URL?> = Observable(URL(string: ""))
    private(set) var errorAction: Observable<String> = Observable("")
    private(set) var presentAction: Observable<Void> = Observable(Void())

    init() {
        setObserver()
    }

    func requestOAuthCode() {
        useCase.requestCode { [weak self] result in
            switch result {
            case let .success(url):
                self?.buttonAction.value = url
            case let .failure(error):
                self?.errorAction.value = error.localizedDescription
            }
        }
    }
}

// MARK: - Private Method
private extension SignInViewModel {
    func setObserver() {
        NotificationCenter.default.addObserver(forName: SceneDelegate.NotificationNames.didGetSignInError, object: nil, queue: nil) { [weak self] notification in
            self?.errorAction.value = notification.description
            }

        NotificationCenter.default.addObserver(forName: SceneDelegate.NotificationNames.didSignIn, object: nil, queue: nil) { [weak self] _ in
            self?.presentAction.notifyObservers()
        }
    }
}
