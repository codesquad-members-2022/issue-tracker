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
    func didChangeTitleText(_ changedText: String?)
    func didChangeContentText(_ changedText: String?)
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
    private let issueManager: EditingIssueManagable

    private(set) var titleText: Observable<String> = Observable("")
    private(set) var contentText: Observable<String> = Observable("")
    private(set) var cancelButtonState: Observable<Bool> = Observable(false)
    private(set) var saveButtonState: Observable<Bool> = Observable(false)
    private(set) var segmentIndex: Observable<Int> = Observable(0)
    private(set) var error: Observable<String> = Observable("")

    init(useCase: EditingIssueManagable) {
        self.issueManager = useCase
    }

    func didChangeSegmentValue(index: Int) {
        segmentIndex.value = index
    }

    func didTouchCancel() {
        cancelButtonState.value = true
    }

    func didTouchSave() {
        var issueEntity = IssueItem(title: titleText.value, content: contentText.value, milestoneName: "", labels: [])

        issueManager.sendNewIssue(issueEntity) { [weak self] (result) in
            switch result {
            case .success(let issueIDDate):
                issueEntity.id = issueIDDate["id"]
                self?.saveButtonState.value = true

                let userInfo = [UserInfoKey.addedIssue: issueEntity]
                NotificationCenter.default.post(
                    name: NotificationNames.didSaveNewIssue,
                    object: self, userInfo: userInfo)

            case .failure(let error):
                self?.saveButtonState.value = false
                self?.error.value = error.message
            }
        }
    }

    func didChangeTitleText(_ changedText: String?) {
        if let changedText = changedText {
            titleText.value = changedText
        }
    }

    func didChangeContentText(_ changedText: String?) {
        if let changedText = changedText {
            contentText.value = changedText
        }
    }
}

extension EditingIssueViewModel {
    enum NotificationNames {
        static let didSaveNewIssue = Notification.Name("EditingIssueViewDidSaveNewIssue")
    }

    enum UserInfoKey {
        static let addedIssue = "AddedIssue"
    }
}
