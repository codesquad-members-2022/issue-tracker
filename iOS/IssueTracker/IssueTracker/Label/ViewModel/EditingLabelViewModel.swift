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
    func setLabelDarkMode(with isDarkMode: Bool)
}

protocol EditingLabelViewModelOutput {
    var titleText: Observable<String?> { get }
    var descriptionText: Observable<String?> { get }
    var backgroundColorText: Observable<String> { get }
    var saveButtonState: Observable<Bool> { get }
    var cancelButtonState: Observable<Bool> { get }
    var isOverFiftyCharactersInTitleText: Observable<Bool> { get }
    var error: Observable<String> { get }
}

protocol EditingLabelViewModelProtocol: EditingLabelViewModelInput, EditingLabelViewModelOutput { }

final class EditingLabelViewModel: EditingLabelViewModelProtocol {
    private(set) var titleText: Observable<String?> = Observable(nil)
    private(set) var descriptionText: Observable<String?> = Observable(nil)
    private(set) var backgroundColorText: Observable<String> = Observable("")
    private(set) var saveButtonState: Observable<Bool> = Observable(false)
    private(set) var cancelButtonState: Observable<Bool> = Observable(false)
    private(set) var isOverFiftyCharactersInTitleText: Observable<Bool> = Observable(false)
    private(set) var error: Observable<String> = Observable("")

    private var isLabelDarkMode = false

    private let useCase: EditingLabelManagable

    init(useCase: EditingLabelManagable) {
        self.useCase = useCase
    }

    func didTouchSave() {
        guard let title = titleText.value else { return }
        var labelEntity = LabelItem(title: title, description: descriptionText.value, backgroundColor: backgroundColorText.value, isDarkMode: String(isLabelDarkMode))

        useCase.addNewLabel(labelEntity) { [weak self] result in
            switch result {
            case .success(let labelIDData):
                labelEntity.id = labelIDData["id"]
                self?.saveButtonState.value = true

                let userInfo = [UserInfoKey.addedLabel: labelEntity]
                NotificationCenter.default.post(name: NotificationNames.didSaveNewLabel, object: self, userInfo: userInfo)
            case .failure(let error):
                self?.error.value = error.message
                self?.saveButtonState.value = false
            }
        }
    }

    func didTouchCancel() {
        cancelButtonState.value = true
    }

    func didTouchGenerateRandomBackgroundColorButton() {
        let randomHexCode = useCase.generateRandomBackgroundColorString()

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
        let randomHexCode = useCase.generateRandomBackgroundColorString()

        backgroundColorText.value = randomHexCode
    }

    func setLabelDarkMode(with isDarkMode: Bool) {
        isLabelDarkMode = isDarkMode
    }
}

extension EditingLabelViewModel {
    enum NotificationNames {
        static let didSaveNewLabel = Notification.Name("EditingLabelViewDidSaveNewLabel")
    }

    enum UserInfoKey {
        static let addedLabel = "AddedLabel"
    }
}
