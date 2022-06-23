//
//  Container.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/22.
//

import UIKit

struct Container {
    // Dependency Container 의 목적 1 : 인스턴스를 하나만 생성해서 관리하기 - 예를 들어 두 뷰컨이 하나의 모델을 사용할 때.
    // Dependency Container 의 목적 2 : 의존성 주입을 통해 객체(VC, 화면 등) 만들어내기
    // 컨테이너는 뷰컨을 찍어내는 곳이기 때문에, 뷰컨 찍어내는데 필요한 요소들을 갖고 있을 수 있다! ex - token
//    private
    enum Screen {
        case login
        case issue(token: String)
        case newIssue
    }
    
    func buildRootViewController() -> UIViewController {
        if let accessToken = GithubUserDefaults.getToken() {
            return buildViewController(.issue(token: accessToken))
        } else {
            return buildViewController(.login)
        }
    }
    
//    private
    func buildViewController(_ screen: Screen) -> UIViewController {
        // 인스턴스 1개로 여러 뷰컨에 활용할 수 있다.
        switch screen {
        case .login:
            return LoginViewController(service: OAuthService())
        case .issue(let token):
            let service = IssueService()
            let model = IssueModel(service: service, token: token)
            let vc = IssueViewController(model: model)
            // Service, Model, View를 모두 들고 있으면서 그들간의 관계를 설정해줄 수 있다.
            // 각 MVVM 내부에서는 관계맺기를 할 필요가 없어진다.
            // 바인딩도 굳이 VC안에서 할 필요가 없어진다.
            // 관계 설정 코드가 복잡해지면 state를 나누어서 상태값 자체를 던져버리면 된다.
//            model.updatedIssues = {
//                vc.collectionView.reloadData()
//            }
            return UINavigationController(rootViewController: vc)
        case .newIssue:
            return NewIssueViewController()
        }
    }
}
