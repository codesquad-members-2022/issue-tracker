//
//  IssueCreateViewController.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/29.
//

import UIKit

class IssueCreateViewController: UIViewController {
    private let saveButton: UIButton = {
        var config = UIButton.Configuration.plain()
        let size = UIImage.SymbolConfiguration(pointSize: 14)
        config.title = "저장"
        config.image = .init(systemName: "plus", withConfiguration: size)
        config.baseForegroundColor = .gray
        config.imagePlacement = .trailing
        let button = UIButton(configuration: config)
        return button
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        configureView()
    }

    deinit {
        print("Deinit: \(#fileID)")
    }

    private func configureView() {
        navigationItem.rightBarButtonItem = .init(customView: saveButton)
    }
}
