//
//  EditingIssueViewModel.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/23.
//

import Foundation
import UIKit

protocol EditingIssueViewModelInput {
    func didTouchCancel()
    func didTouchSave()
    func didChangeSegmentValue(index: Int)
}

protocol EditingIssueViewModelOutput {
    var titleText: Observable<String> { get }
    var contentText: Observable<String> { get }
    var cancelButtonState: Observable<Bool> { get }
    var saveButtonState: Observable<Bool> { get }
    var segmentIndex: Observable<Int> { get }
    var error: Observable<String> { get }
}

protocol EditingIssueViewModelProtocol: EditingIssueViewModelInput, EditingIssueViewModelOutput { }

final class EditingIssueViewModel: EditingIssueViewModelProtocol {
    private let useCase: EditingIssueManagable

    private(set) var titleText: Observable<String> = Observable("")
    private(set) var contentText: Observable<String> = Observable("")
    private(set) var cancelButtonState: Observable<Bool> = Observable(false)
    private(set) var saveButtonState: Observable<Bool> = Observable(false)
    private(set) var segmentIndex: Observable<Int> = Observable(0)
    private(set) var error: Observable<String> = Observable("")

    init(useCase: EditingIssueManagable) {
        self.useCase = useCase
    }

    func didChangeSegmentValue(index: Int) {
        segmentIndex.value = index
    }

    func didTouchCancel() {
        cancelButtonState.value = true
    }

    func didTouchSave() {
        saveButtonState.value = true
        var issueEntity = IssueItem(id: nil, title: titleText.value, content: contentText.value, milestoneName: "", labels: [])

        useCase.sendNewIssue(issueEntity) { [weak self] (result) in
            switch result {
            case .success(let idDictionary):
                guard let id = idDictionary["id"] else { return }
                issueEntity.id = id
            case .failure(let error):
                self?.error.value = error.localizedDescription
            }
        }
    }
}
