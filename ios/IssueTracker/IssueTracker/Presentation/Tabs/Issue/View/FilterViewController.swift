//
//  FilterViewController.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/28.
//

import UIKit

class FilterViewController: UIViewController {
    private let cancelButton = UIBarButtonItem(barButtonSystemItem: .cancel, target: nil, action: nil)
    private let backButton = UIBarButtonItem(title: "뒤로", style: .plain, target: nil, action: nil)

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        configureView()
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    private func configureView() {
        navigationItem.rightBarButtonItem = cancelButton
        backButton.tintColor = .black
        navigationItem.leftBarButtonItem = backButton
        title = "dddddddddd"
    }
}
