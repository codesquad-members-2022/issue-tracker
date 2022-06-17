//
//  MockDataService.swift
//  PRTracker
//
//  Created by 안상희 on 2022/06/17.
//

import Foundation

final class MockDataService {
    func getMockPRList(completion: ([PullListModel]?) -> Void) {
        let model1 = PullListModel(title: "PR 제목입니당",
                                 content: "작업 내용은 다음과 같습니다,,\n클릭해서 봐주세요..",
                                 projectName: "PR 마스터프로젝트",
                                 labelName: "Documentation",
                                 commentCount: 2)
        let model2 = PullListModel(title: "PR 제목입니당2",
                                  content: "작업 내용은 다음과 같습니다,,",
                                  projectName: "PR 마스터프로젝트2",
                                  labelName: "Documentation",
                                  commentCount: 2)
        completion([model1, model2])
    }
}
