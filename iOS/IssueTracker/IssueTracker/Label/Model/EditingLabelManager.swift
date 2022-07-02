//
//  EditingIssueManager.swift
//  IssueTracker
//
//  Created by 김한솔 on 2022/06/30.
//

import Foundation

protocol EditingLabelManagable {
    typealias LabelidData = [String: Int]

    func generateRandomBackgroundColorString() -> String
    func addNewLabel(_ newLabelEntity: LabelItem, completion: @escaping (Result<LabelidData, NetworkError>) -> Void)
}

struct EditingLabelManager {
    private let urlSession: URLSessionProtocol

    init(urlSession: URLSessionProtocol = URLSession.shared) {
        self.urlSession = urlSession
    }
}

extension EditingLabelManager: EditingLabelManagable {
    func generateRandomBackgroundColorString() -> String {
        let hexCode = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "A", "B", "C", "D", "E", "F"]
        return "#".appending(hexCode[Int.random(in: 0..<15)])
            .appending(hexCode[Int.random(in: 0..<15)])
            .appending(hexCode[Int.random(in: 0..<15)])
            .appending(hexCode[Int.random(in: 0..<15)])
            .appending(hexCode[Int.random(in: 0..<15)])
            .appending(hexCode[Int.random(in: 0..<15)])
    }

    func addNewLabel(_ newLabelEntity: LabelItem, completion: @escaping (Result<LabelidData, NetworkError>) -> Void) {
        NetworkService<LabelidData>.fetchData(target: LabelNetworkTarget.addNewLabel(newLabel: newLabelEntity), urlSession: urlSession, completion: completion)
    }
}
