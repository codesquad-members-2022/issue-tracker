//
//  MilestoneViewController.swift
//  IssueTracker
//
//  Created by 송태환 on 2022/06/24.
//

import UIKit

final class MilestoneViewController: UIViewController {
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .systemBackground
    }

    deinit {
        print("Deinit: \(#fileID)")
    }
}
