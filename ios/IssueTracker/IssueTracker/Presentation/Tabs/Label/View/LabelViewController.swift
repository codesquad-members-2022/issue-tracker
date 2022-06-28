//
//  LabelViewController.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

final class LabelViewController: UIViewController {
    private let addButton: UIButton = {
        var configuration = UIButton.Configuration.plain()
        configuration.title = "추가"
        configuration.image = UIImage(systemName: "plus")
        configuration.imagePlacement = .trailing

        let button = UIButton(configuration: configuration)

        return button
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        configureUI()
    }

    private func configureUI() {
        view.backgroundColor = .systemBackground
        configureNavigationBar()
    }

    private func configureNavigationBar() {
        navigationItem.title = "레이블"
        navigationController?.navigationBar.prefersLargeTitles = true
        navigationItem.rightBarButtonItem = .init(customView: addButton)
    }

    deinit {
        print("Deinit: \(#fileID)")
    }
}
