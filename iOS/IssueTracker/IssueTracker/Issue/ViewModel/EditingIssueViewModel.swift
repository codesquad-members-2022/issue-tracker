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
    func didChangeSegmentValue(index: Int)
}

protocol EditingIssueViewModelOutput {
    var titleText: Observable<String> { get }
    var contentText: Observable<String> { get }
    var cancelButtonState: Observable<Bool> { get }
    var segmentIndex: Observable<Int> { get }
}

protocol EditingIssueViewModelProtocol: EditingIssueViewModelInput, EditingIssueViewModelOutput { }

final class EditingIssueViewModel: EditingIssueViewModelProtocol {
    private(set) var titleText: Observable<String> = Observable("")
    private(set) var contentText: Observable<String> = Observable("")
    private(set) var cancelButtonState: Observable<Bool> = Observable(false)
    private(set) var segmentIndex: Observable<Int> = Observable(0)

    func didChangeSegmentValue(index: Int) {
        segmentIndex.value = index
    }

    func didTouchCancel() {
        cancelButtonState.value = true
    }
}
