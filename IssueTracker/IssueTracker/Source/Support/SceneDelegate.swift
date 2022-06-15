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

        let validURL = IssueTrackerTarget.requestAccessToken(code: authorizedCode)
//        guard let requestURL = createRequest(validURL) else { return }

//        URLSession.shared.dataTask(with: requestURL) { data, _, _ in
//
//            if let data = data {
//                print(data)
//            }
//
//        }.resume()
    }

    private func createRequest(_ target: BaseTarget) -> URLRequest? {
        guard let baseUrl = target.baseURL else {
            return nil
        }

        var url = baseUrl
        if let path = target.path {
            url = baseUrl.appendingPathComponent(path)
        }

        var request = URLRequest(url: url)
        request.httpMethod = target.method.value

        if let param = target.parameter,
           let body = try? JSONSerialization.data(withJSONObject: param, options: .init()) {
                request.httpBody = body
            }

        return request
    }

}
