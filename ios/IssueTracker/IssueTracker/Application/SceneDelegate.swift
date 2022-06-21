//
//  SceneDelegate.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/14.
//

import UIKit

class SceneDelegate: UIResponder, UIWindowSceneDelegate {
    var window: UIWindow?
    var appCoordinator: AppFlowCoordinator?

    func scene(_ scene: UIScene, willConnectTo _: UISceneSession, options _: UIScene.ConnectionOptions) {
        guard let scene = (scene as? UIWindowScene) else {
            return
        }

        let navigationController = UINavigationController()
        let appContainer = AppFlowDIContainer()

        appCoordinator = AppFlowCoordinator(navigationController: navigationController, dependency: appContainer)

        appCoordinator?.start()

        window = UIWindow(windowScene: scene)
        window?.rootViewController = navigationController
        window?.makeKeyAndVisible()
    }

    // MARK: - URL Scheme
    func scene(_: UIScene, openURLContexts URLContexts: Set<UIOpenURLContext>) {
        // Determine who sent the URL.
        if let urlContext = URLContexts.first {
            let sendingAppID = urlContext.options.sourceApplication
            let url = urlContext.url
            print("source application = \(sendingAppID ?? "Unknown")")
            print("url = \(url)")

            // Process the URL similarly to the UIApplicationDelegate example.
            guard let components = NSURLComponents(url: url, resolvingAgainstBaseURL: true), let path = components.path, let params = components.queryItems else {
                return
            }

            print("path = \(path)")
            print("params = \(params)")

            if let code = params.first(where: { $0.name == "code" })?.value {
                print("Code = \(code)")
            } else {
                print("Code missing")
            }
        }
    }

    // MARK: - Universal Links
    func scene(_: UIScene, continue userActivity: NSUserActivity) {
        // Get URL components from the incoming user activity.
        guard userActivity.activityType == NSUserActivityTypeBrowsingWeb, let incomingURL = userActivity.webpageURL, let components = NSURLComponents(url: incomingURL, resolvingAgainstBaseURL: true) else {
            return
        }

        // Check for specific URL components that you need.
        guard let path = components.path, let params = components.queryItems else {
            return
        }

        print("path = \(path)")
        print("params = \(params)")

        if let code = params.first(where: { $0.name == "code" })?.value {
            print("Code = \(code)")
        } else {
            print("Code missing")
        }
    }
}
