//
//  EditingLabelViewModel.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/29.
//

import Foundation

protocol EditingLabelViewModelInput {
    func didTouchCancel()
    func didTouchSave()
    func didTouchGenerateRandomBackgroundColorButton()
    func didChangedTitleTextField(text: String?)
    func didChangedDescriptionTextField(text: String?)
    func viewDidLoad()
}

protocol EditingLabelViewModelOutput {
    var titleText: Observable<String?> { get }
    var descriptionText: Observable<String?> { get }
    var backgroundColorText: Observable<String> { get }
    var saveButtonState: Observable<Bool> { get }
    var cancelButtonState: Observable<Bool> { get }
    var isOverFiftyCharactersInTitleText: Observable<Bool> { get }
}

protocol EditingLabelViewModelProtocol: EditingLabelViewModelInput, EditingLabelViewModelOutput { }

final class EditingLabelViewModel: EditingLabelViewModelProtocol {
    private(set) var titleText: Observable<String?> = Observable(nil)
    private(set) var descriptionText: Observable<String?> = Observable(nil)
    private(set) var backgroundColorText: Observable<String> = Observable("")
    private(set) var saveButtonState: Observable<Bool> = Observable(false)
    private(set) var cancelButtonState: Observable<Bool> = Observable(false)
    private(set) var isOverFiftyCharactersInTitleText: Observable<Bool> = Observable(false)

    func didTouchSave() {
        saveButtonState.value = true
    }

    func didTouchCancel() {
        cancelButtonState.value = true
    }

    func didTouchGenerateRandomBackgroundColorButton() {
        let randomHexCode = generateRandomBackgroundColorString()

        backgroundColorText.value = randomHexCode
    }

    func didChangedTitleTextField(text: String?) {
        titleText.value = text

        guard let text = text else { return }
        isOverFiftyCharactersInTitleText.value = text.count > 50
    }

    func didChangedDescriptionTextField(text: String?) {
        descriptionText.value = text
    }

    func viewDidLoad() {
        let randomHexCode = generateRandomBackgroundColorString()

        backgroundColorText.value = randomHexCode
    }
}

private extension EditingLabelViewModel {
    func generateRandomBackgroundColorString() -> String {
        let hexCode = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F"]
        return "#".appending(hexCode[Int.random(in: 0..<15)])
            .appending(hexCode[Int.random(in: 0..<15)])
            .appending(hexCode[Int.random(in: 0..<15)])
            .appending(hexCode[Int.random(in: 0..<15)])
            .appending(hexCode[Int.random(in: 0..<15)])
            .appending(hexCode[Int.random(in: 0..<15)])
    }
}
