//
//  SignInManager.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/15.
//

import Foundation
import UIKit

struct SignInManager {
    static let shared = SignInManager()

    private let clientID = Bundle.main.clientID
    private let clientSecret = Bundle.main.clientSecret

    func requestCode() {
        let baseURL = "https://github.com/login/oauth/authorize"
        var components = URLComponents(string: baseURL) ?? URLComponents()
        components.queryItems = [
            URLQueryItem(name: "client_id", value: clientID)
        ]

        guard let url = components.url else {
            return print("url Error")
        }
        UIApplication.shared.open(url)
    }

    func requestAccessToken(with code: String) {
        let url = "https://github.com/login/oauth/access_token"
        var components = URLComponents(string: url) ?? URLComponents()

        components.queryItems = [
            URLQueryItem(name: "client_id", value: clientID),
            URLQueryItem(name: "client_secret", value: clientSecret),
            URLQueryItem(name: "code", value: code)
        ]

        if let request = makePostRequest(with: components.url) {
            URLSession.shared.dataTask(with: request) { (data, response, error) in
                guard error == nil else {
                    return print(error?.localizedDescription as Any)
                }
                guard let data = data else {
                    return print("no data")
                }
                guard let response = response as? HTTPURLResponse else {
                    return print("no response")
                }

                let statusCode = response.statusCode
                guard 200..<300 ~= statusCode else {
                    return print("invalid response status Code")
                }

                guard let decodedData = try? JSONDecoder().decode([String: String].self, from: data) else {
                    return print("Decoding Error")
                }

                print(decodedData["access_token"] ?? "no Accesstoken")
            }.resume()
        }
    }
}

private extension SignInManager {
    func makePostRequest(with url: URL?) -> URLRequest? {
        guard let url = url else {
            return nil
        }

        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.addValue("application/json", forHTTPHeaderField: "Accept")

        return request
    }
}
