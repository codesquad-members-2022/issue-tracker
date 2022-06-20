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
    /* SignInViewModelActions의 presentTabBarController 프로퍼티를 SceneDelegate에서 DI시에 설정해주려고 하니,
     SceneDelegate에는 `present()` 메소드가 없어서 해당 프로퍼티를 설정할 수 없었음.
     그로인해, SignInVC에서 해당 프로퍼티를 설정해줄 수 있도록 메소드 제공 */
    func setPresentAction(_ action: @escaping () -> Void)
}

struct SignInViewModelActions {
    let openURL: (URL) -> Void
    var presentTabBarController: () -> Void = { }
}

final class SignInViewModel: SignInViewModelProtocol {
    private(set) var error: Observable<String> = Observable("")
    private let useCase: SignInManagable
    private var actions: SignInViewModelActions?

    init(useCase: SignInManagable,
         actions: SignInViewModelActions) {
        self.useCase = useCase
        self.actions = actions
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

    func setPresentAction(_ action: @escaping () -> Void) {
        actions?.presentTabBarController = action
    }
}

// MARK: - Private Method
private extension SignInViewModel {
    func setObserver() {
        NotificationCenter.default.addObserver(forName: SceneDelegate.NotificationNames.didGetSignInError, object: nil, queue: nil) { [weak self] notification in
            self?.error.value = notification.description
            }

        NotificationCenter.default.addObserver(forName: SceneDelegate.NotificationNames.didSignIn, object: nil, queue: nil) { [weak self] _ in
            self?.actions?.presentTabBarController()
        }
    }
}
