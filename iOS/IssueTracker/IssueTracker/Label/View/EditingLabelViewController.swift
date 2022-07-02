//
//  EditingLabelViewController.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import UIKit

final class EditingLabelViewController: UIViewController {
    private let navigationItems = EditingLabelViewNavigationItems()
    private let editingLabelView: EditingLabelView

    private var viewModel: EditingLabelViewModelProtocol

    init(viewModel: EditingLabelViewModelProtocol) {
        self.viewModel = viewModel
        self.editingLabelView = EditingLabelView(viewModel: viewModel)
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setNavigationItems()
        view.addSubview(editingLabelView)
        view.backgroundColor = .background2
        setSubviewsLayout()
        bind(to: viewModel)
        viewModel.viewDidLoad()
    }
}

private extension EditingLabelViewController {
    func setNavigationItems() {
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: navigationItems.cancelButton)
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: navigationItems.saveButton)

        navigationItems.saveButton.isEnabled = false
        navigationItem.title = "새로운 레이블"
    }

    func setSubviewsLayout() {
        let screenSize = UIScreen.main.bounds.size
        editingLabelView.snp.makeConstraints { make in
            make.top.equalTo(view).offset(100/812 * screenSize.height)
            make.leading.trailing.equalTo(view)

        }
    }

    func bind(to viewModel: EditingLabelViewModelProtocol) {
        viewModel.cancelButtonState.bind(on: self) { [weak self] _ in
            self?.dismiss(animated: true)
        }
        viewModel.saveButtonState.bind(on: self) { [weak self] isSaved in
            if isSaved { self?.dismissViewController() }
        }
        viewModel.titleText.bind(on: self) { [weak self] titleText in
            self?.changePreviewLabelText(with: titleText)
            self?.updateSaveButtonState(with: titleText)
        }
        viewModel.backgroundColorText.bind(on: self) { [weak self] backgroundColorText in
            self?.changePreviewLabelBackgroundColor(with: backgroundColorText)
            self?.editingLabelView.updateSelectedBackgroundLabel(with: backgroundColorText)
        }
        viewModel.error.bind(on: self) { [weak self] errorMessage in
            self?.showAlert(of: errorMessage)
        }

        navigationItems.cancelButton.addAction(UIAction { [weak self] _ in
            self?.viewModel.didTouchCancel()
        }, for: .touchUpInside)
        navigationItems.saveButton.addAction(UIAction { [weak self] _ in
            self?.viewModel.didTouchSave()
        }, for: .touchUpInside)
    }

    func changePreviewLabelText(with text: String?) {
        editingLabelView.updatePreviewLabelText(with: text)
    }

    func updateSaveButtonState(with titleText: String?) {
        if let titleText = titleText,
           !titleText.isEmpty {
            navigationItems.saveButton.isEnabled = true
        } else {
            navigationItems.saveButton.isEnabled = false
        }
        
    }

    func changePreviewLabelBackgroundColor(with backgroundColorText: String) {
        editingLabelView.updatePreviewLabelBackgroundColor(with: backgroundColorText)
    }

    func showAlert(of errorMessage: String) {
        DispatchQueue.main.async { [weak self] in
            let alert = UIAlertController(title: "오류가 발생했습니다.", message: errorMessage, preferredStyle: .alert)
            let alertAction = UIAlertAction(title: "OK", style: .cancel)
            alert.addAction(alertAction)
            self?.present(alert, animated: true)
        }
    }

    func dismissViewController() {
        DispatchQueue.main.async { [weak self] in
            self?.dismiss(animated: true)
        }
    }
}
