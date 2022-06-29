//
//  EditingLabelViewController.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import UIKit

final class EditingLabelViewController: UIViewController {
    private let navigationItems = EditingLabelViewNavigationItems()
    private let editingLabelListView = EditingLabelView()

    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationItems()
        view.addSubview(editingLabelListView)
        view.backgroundColor = .background2
        setSubviewsLayout()
    }
}

private extension EditingLabelViewController {
    func setNavigationItems() {
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: navigationItems.cancelButton)
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: navigationItems.saveButton)

        navigationItem.title = "새로운 레이블"
    }

    func setSubviewsLayout() {
        let screenSize = UIScreen.main.bounds.size
        editingLabelListView.snp.makeConstraints { make in
            make.top.equalTo(view).offset(100/812 * screenSize.height)
            make.leading.trailing.equalTo(view)

        }
    }
}
