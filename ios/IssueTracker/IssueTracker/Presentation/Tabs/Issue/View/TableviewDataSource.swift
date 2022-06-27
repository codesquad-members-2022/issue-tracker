//
//  TableviewDataSource.swift
//  IssueTracker
//
//  Created by Jihee hwang on 2022/06/24.
//

import UIKit

struct MockData {
    var title: String
    var description: String
    var milestoneTitle: String
    var badgeColor: UIColor
    var badgeTitle: String

    init(title: String, description: String, milestoneTitle: String, badgeColor: UIColor, badgeTitle: String) {
        self.title = title
        self.description = description
        self.milestoneTitle = milestoneTitle
        self.badgeColor = badgeColor
        self.badgeTitle = badgeTitle
    }

    static var mockData = [
        MockData(title: "이슈트래커", description: "6월 13일 월요일부터 7월 1일 금요일까지\nDonggi, Alex, Jee", milestoneTitle: "마스터즈코스", badgeColor: .gray, badgeTitle: "마스터즈"),
        MockData(title: "마스터즈 코스", description: "한 주밖에 안남은거 실화인가여ㅑ...........", milestoneTitle: "오우노우", badgeColor: .darkGray, badgeTitle: "안돼엨"),
        MockData(title: "Donggi", description: "동기 비동기", milestoneTitle: "sync and async", badgeColor: .brown, badgeTitle: "동기"),
        MockData(title: "Alex", description: "누가 알렉사우르스라고 했던거같은데", milestoneTitle: "알렉스", badgeColor: .purple, badgeTitle: "알렉스는 내친구"),
        MockData(title: "이슈트래커", description: "6월 13일 월요일부터 7월 1일 금요일까지", milestoneTitle: "마스터즈코스", badgeColor: .gray, badgeTitle: "마스터즈"),
        MockData(title: "이슈트래커", description: "6월 13일 월요일부터 7월 1일 금요일까지", milestoneTitle: "마스터즈코스", badgeColor: .gray, badgeTitle: "마스터즈"),
        MockData(title: "이슈트래커", description: "6월 13일 월요일부터 7월 1일 금요일까지", milestoneTitle: "마스터즈코스", badgeColor: .gray, badgeTitle: "마스터즈")
    ]
}

final class TableviewDataSource: NSObject, UITableViewDataSource {
    var mockData = MockData.mockData

    func tableView(_: UITableView, numberOfRowsInSection _: Int) -> Int {
        mockData.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: IssueListCell.identifier, for: indexPath) as? IssueListCell else {
            return UITableViewCell()
        }
        let target = mockData[indexPath.row]
        cell.setInfo(title: target.title, description: target.description, milestoneTitle: target.milestoneTitle, badgeColor: target.badgeColor, badgeTitle: target.title)

        return cell
    }

    func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            tableView.beginUpdates()
            mockData.remove(at: indexPath.row)
            tableView.deleteRows(at: [indexPath], with: .fade)
            tableView.endUpdates()
        }
    }
}
