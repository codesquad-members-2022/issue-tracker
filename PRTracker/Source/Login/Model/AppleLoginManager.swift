//
//  AppleLoginManager.swift
//  PRTracker
//
//  Created by Bumgeun Song on 2022/06/16.
//

import Foundation
import AuthenticationServices

final class AppleLoginManager: NSObject, ASAuthorizationControllerDelegate {
    weak var viewController: UIViewController?
    
    func authorizationController(controller: ASAuthorizationController, didCompleteWithAuthorization authorization: ASAuthorization) {
        if let _ = authorization.credential as? ASAuthorizationAppleIDCredential {
            // TODO: 애플 로그인 완료 시 처리 방법 확정 필요
        }
    }
    
    func authorizationController(controller: ASAuthorizationController, didCompleteWithError error: Error) {
        Log.error("\(error)")
    }
}
