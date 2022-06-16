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

        
        //TODO: Access token 유효성 검사 후 화면 흐름 제어하기

        window?.rootViewController = ViewController()
        window?.makeKeyAndVisible()
    }

    func scene(_ scene: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        // Authorized code
        guard let response = URLContexts.first?.url,
        let authorizedCode = response.absoluteString.components(separatedBy: "code=").last else { return }
        let targetUrl: IssueTrackerTarget = .requestAccessToken(code: authorizedCode)
        print(targetUrl)

        // authorizedCode를 넣은 IssueTarget생성
        // 얘로 validURL을 만들어서 POST 요청을 보내야함
        let target = IssueTrackerTarget.requestAccessToken(code: authorizedCode)

        guard let requestURL = createRequest(target) else { return }

        URLSession.shared.dataTask(with: requestURL) { data, _, error in

            guard error == nil else { return }
            guard let data = data else { return }
            guard let jsonData = try! JSONSerialization.jsonObject(with: data) as? [String: Any] else { return }

            //TODO: access Token 저장 방식 변경
            UserDefaults.standard.set("access_token", forKey: jsonData["access_token"] as! String)
            
            DispatchQueue.main.async {
                self.window?.rootViewController = TabBarViewController()
            }

        }.resume()

    }

    private func createRequest(_ target: BaseTarget) -> URLRequest? {
        // base url
        guard var url = target.baseURL else {
            return nil
        }
        // parameter append
        if let path = target.path {
            url = url.appendingPathComponent(path)
        }
        var request = URLRequest(url: url)

        // paramter를 Json으로 encode
        if let param = target.parameter {
            let requestBody = try! JSONSerialization.data(withJSONObject: target.parameter!, options: .init())
            request.httpBody = requestBody
        }

        // 우리가 전송하는 헤더의 타입 설정
        request.setValue(target.content.value, forHTTPHeaderField: target.content.forHTTPHeaderField)

        // 응답으로 받아오는 data 의 타입 설정
        if let accept = target.accept {
            request.addValue(accept.value, forHTTPHeaderField: accept.forHTTPHeaderField)
        }

        request.httpMethod = target.method.value

        return request
    }

}
