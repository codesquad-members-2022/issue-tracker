//
//  EditingIssueViewController.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/21.
//

import SnapKit

final class EditingIssueViewController: UIViewController {
    private let titleViewComponents = EditingIssueTitleViewComponents()
    private let contentViewComponents = EditingIssueContentViewComponents()
    private let navigationItems = EditingIssueViewNavigationItems()

    private let textViewDelegate = EditingIssueTextViewDelegate()

    private var viewModel: EditingIssueViewModelProtocol

    init(viewModel: EditingIssueViewModelProtocol) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
   
    override func viewDidLoad() {
        super.viewDidLoad()
        view.addSubviews(titleViewComponents, contentViewComponents)
        setDelegate()
    }

    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        view.backgroundColor = .white
        setNavigationItems()
        bind(to: viewModel)
    }

    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        let screenSize = UIScreen.main.bounds.size

        titleViewComponents.snp.makeConstraints { make in
            make.top.equalTo(view.safeAreaLayoutGuide)
            make.height.equalTo(43.5/812 * screenSize.height)
            make.leading.equalTo(view).offset(16/375 * screenSize.width)
            make.trailing.equalTo(view).inset(16/375 * screenSize.width)
        }

        contentViewComponents.snp.makeConstraints { make in
            make.top.equalTo(titleViewComponents.snp.bottom)
            make.leading.trailing.equalTo(titleViewComponents)
            make.height.equalTo(411/812 * screenSize.height)
        }
        
    }
}

// MARK: - private methods
private extension EditingIssueViewController {
    func bind(to viewModel: EditingIssueViewModelProtocol) {
        viewModel.titleText.bind(on: self) { [weak self] _ in
            self?.updateSaveButtonState()
        }
        viewModel.contentText.bind(on: self) { [weak self] _ in
            self?.updateSaveButtonState()
        }
        viewModel.cancelButtonState.bind(on: self) { [weak self] _ in
            self?.popViewController()
        }
        viewModel.saveButtonState.bind(on: self) { [weak self] isSaved in
            if isSaved { self?.popViewController() }
        }
        viewModel.segmentIndex.bind(on: self) { [weak self] selectedIndex in
            self?.didSegmentValueChanged(selectedIndex)
        }
        viewModel.error.bind(on: self) { [weak self] errorDescription in
            self?.showAlert(of: errorDescription)
        }

        navigationItems.selectContentViewSegment.addAction(UIAction { [weak self] _ in
            guard let self = self else { return }

            let selectedIndex = self.navigationItems.selectContentViewSegment.selectedSegmentIndex
            viewModel.didChangeSegmentValue(index: selectedIndex)
        }, for: .valueChanged)

        navigationItems.cancelButton.addAction(UIAction { _ in
            viewModel.didTouchCancel()
        }, for: .touchUpInside)

        navigationItems.saveButton.addAction(UIAction { _ in
            viewModel.didTouchSave()
        }, for: .touchUpInside)

        titleViewComponents.titleTextField.addAction(UIAction { [weak self] _ in
            viewModel.didChangeTitleText(self?.titleViewComponents.titleTextField.text)
        }, for: .editingChanged)
    }

    func setDelegate() {
        contentViewComponents.editableContentTextView.delegate = textViewDelegate
        textViewDelegate.setTextViewAction(viewModel.didChangeContentText(_:))
    }

    func setNavigationItems() {
        navigationItem.largeTitleDisplayMode = .never
        navigationItem.titleView = navigationItems.selectContentViewSegment
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: navigationItems.saveButton)
        navigationItem.rightBarButtonItem?.isEnabled = false
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: navigationItems.cancelButton)
    }

    func didSegmentValueChanged(_ selectedIndex: Int) {
        selectedIndex == 0 ?
        contentViewComponents.showEditableContentTextView() : contentViewComponents.showPreviewTextView()
    }

    func updateSaveButtonState() {
        let isTitleTextEmpty = viewModel.titleText.value.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty
        let isContentTextEmpty = viewModel.contentText.value.trimmingCharacters(in: .whitespacesAndNewlines).isEmpty

        navigationItem.rightBarButtonItem?.isEnabled = !isTitleTextEmpty && !isContentTextEmpty
    }

    func popViewController() {
        DispatchQueue.main.async {
            self.navigationController?.popViewController(animated: true)
        }
    }

    func showAlert(of errorMessage: String) {
        DispatchQueue.main.async { [weak self] in
            let alert = UIAlertController(title: "save 버튼을 다시 눌러주세요.", message: errorMessage, preferredStyle: .alert)
            let alertAction = UIAlertAction(title: "OK", style: .cancel)
            alert.addAction(alertAction)
            self?.present(alert, animated: true)
        }
    }
}
