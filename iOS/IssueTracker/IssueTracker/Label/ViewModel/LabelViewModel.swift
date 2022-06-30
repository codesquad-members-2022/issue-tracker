//
//  LabelViewModel.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/28.
//

import Foundation

protocol LabelViewModelInput {
    func viewDidLoad()
    func didTouchAddButton()
    func didLabelSelected(index: Int)
    func didAddNewLabel(_ label: LabelItem)
}

protocol LabelViewModelOutput {
    var labels: Observable<[LabelItem]> { get }
    var selectedLabelIndex: Observable<Int> { get }
    var addButtonState: Observable<Bool> { get }
    var error: Observable<String> { get }
}

protocol LabelViewModelProtocol: LabelViewModelInput, LabelViewModelOutput { }

final class LabelViewModel: LabelViewModelProtocol {
    private(set) var labels: Observable<[LabelItem]> = Observable([])
    private(set) var selectedLabelIndex: Observable<Int> = Observable(-1)
    private(set) var addButtonState: Observable<Bool> = Observable(false)
    private(set) var error: Observable<String> = Observable("")

    private let labelManager: LabelManagable

    init(labelManager: LabelManagable) {
        self.labelManager = labelManager
        addObserver()
    }

    func viewDidLoad() {
        labelManager.getLabels { [weak self] result in
            switch result {
            case .success(let labelData):
                self?.labels.value = labelData
            case .failure(let error):
                self?.error.value = error.message
            }
        }
    }

    func didTouchAddButton() {
        addButtonState.value = true
    }
    
    func didLabelSelected(index: Int) {
        selectedLabelIndex.value = index
    }

    func didAddNewLabel(_ newLabel: LabelItem) {
        labels.value.append(newLabel)
    }
}

private extension LabelViewModel {
    func addObserver() {
        NotificationCenter.default.addObserver(forName: EditingLabelViewModel.NotificationNames.didSaveNewLabel, object: nil, queue: nil) { [weak self] notification in
            guard let addedLabel = notification.userInfo?[EditingLabelViewModel.UserInfoKey.addedLabel] as? LabelItem else { return }

            self?.didAddNewLabel(addedLabel)
        }
    }
}
