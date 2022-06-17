//
//  MockDataService.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/17.
//

import Foundation

final class MockDataService {
    func getMockPullList(completion: ([Pull]?) -> Void) {
        let user1 = User(id: 0, name: "Eddy", reposURL: "url~")
        let user2 = User(id: 1, name: "Selina", reposURL: "url!")
        let label1 = Label(id: 0, color: "Blue", name: "Documentation")
        let label2 = Label(id: 1, color: "Yello", name: "Refactor")
        let milestone = Milestone(id: 0, title: "마일스토온")
        let model1 = Pull(id: 0,
                          title: "PR 제목입니당",
                          body: "작업 내용은 다음과 같습니다,,\n클릭해서 봐주세요..",
                          state: "상태1",
                          creator: user1,
                          assignees: [user1],
                          labels: [label1],
                          milestone: milestone)
        let model2 = Pull(id: 1,
                          title: "PR 제목입니당2",
                          body: "작업 내용은 다음과 같습니다,,\n클릭해서 봐주세욤",
                          state: "상태2",
                          creator: user2,
                          assignees: [user1, user2],
                          labels: [label1, label2],
                          milestone: milestone)
        completion([model1, model2])
    }
}
