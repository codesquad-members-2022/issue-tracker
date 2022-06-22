//
//  SignInViewModel.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/17.
//

import Foundation

protocol SignInViewModelInput {
    func didSelect()
}

protocol SignInViewModelOutput {
    var error: Observable<String> { get }
}

protocol SignInViewModelProtocol: SignInViewModelInput, SignInViewModelOutput {
    func setOpenURLAction(_ action: @escaping (URL) -> Void)
}

struct SignInViewModelActions {
    let openURL: (URL) -> Void
}

final class SignInViewModel: SignInViewModelProtocol {
    private(set) var error: Observable<String> = Observable("")
    private let useCase: SignInManagable
    private var actions: SignInViewModelActions?

    init(useCase: SignInManagable) {
        self.useCase = useCase
        setObserver()
    }

    func didSelect() {
        useCase.requestCode { [weak self] (result) in
            switch result {
            case .success(let url):
                self?.actions?.openURL(url)
            case .failure(let error):
                self?.error.value = error.localizedDescription
            }
        }
    }

    func setOpenURLAction(_ action: @escaping (URL) -> Void) {
        actions = SignInViewModelActions(openURL: action)
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
