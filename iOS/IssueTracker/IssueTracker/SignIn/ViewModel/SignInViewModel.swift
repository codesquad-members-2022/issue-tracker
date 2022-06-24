//
//  SignInViewModel.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/17.
//

import Foundation

protocol SignInViewModelInput {
    func didTouchGitHubSignIn()
}

protocol SignInViewModelOutput {
    var gitHubSignInURL: Observable<URL?> { get }
    var error: Observable<String> { get }
}

protocol SignInViewModelProtocol: SignInViewModelInput, SignInViewModelOutput { }

final class SignInViewModel: SignInViewModelProtocol {
    private(set) var gitHubSignInURL: Observable<URL?> = Observable(URL(string: ""))
    private(set) var error: Observable<String> = Observable("")
    private let useCase: SignInManagable

    init(useCase: SignInManagable) {
        self.useCase = useCase
        setObserver()
    }

    func didTouchGitHubSignIn() {
        useCase.requestCode { [weak self] (result) in
            switch result {
            case .success(let url):
                self?.gitHubSignInURL.value = url
            case .failure(let error):
                self?.error.value = error.localizedDescription
            }
        }
    }
}

// MARK: - Private Method
private extension SignInViewModel {
    func setObserver() {
        NotificationCenter.default.addObserver(forName: SceneDelegate.NotificationNames.didGetSignInError, object: nil, queue: nil) { [weak self] notification in
            self?.error.value = notification.description
            }
    }
}
