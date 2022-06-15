//
//  SceneDelegate.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/13.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = (scene as? UIWindowScene) else { return }
        window = UIWindow(windowScene: windowScene)

//        window?.rootViewController = TabBarViewController()
        window?.rootViewController = ViewController()
        window?.makeKeyAndVisible()
    }

    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        // Authorized code
        guard let response = URLContexts.first?.url,
        let authorizedCode = response.absoluteString.components(separatedBy: "code=").last else { return }
        let targetUrl: IssueTrackerTarget = .requestAccessToken(code: authorizedCode)
        print(targetUrl)
    }

    private static func createRequest(_ target: BaseTarget) -> URLRequest? {
        guard let baseUrl = target.baseURL else {
            return nil
        }

        var url = baseUrl
        if let path = target.path {
            url = baseUrl.appendingPathComponent(path)
        }

        var request = URLRequest(url: url)
        request.httpMethod = target.method.value
//        target.parameter?.forEach(<#T##body: ((key: String, value: String)) throws -> Void##((key: String, value: String)) throws -> Void#>)
//        target.header?.forEach { key, value in
//            request.addValue(value, forHTTPHeaderField: key)
//        }
//
//        if target.content == .urlencode {
//            if let param = target.parameter {
//                let formDataString = param.reduce(into: "") {
//                    $0 = $0 + "\($1.key)=\($1.value)&"
//                }.dropLast()
//
//                request.httpBody = formDataString.data(using: .utf8, allowLossyConversion: true)
//            }
//        } else {
//            if let param = target.parameter,
//               let body = try? JSONSerialization.data(withJSONObject: param, options: .init()) {
//                request.httpBody = body
//            }
//        }
        return request
    }
}
