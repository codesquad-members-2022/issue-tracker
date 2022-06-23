//
//  IssueListViewModelTests.swift
//  PRTrackerTests
//
//  Created by 안상희 on 2022/06/23.
//

import XCTest

class IssueListViewModelTests: XCTestCase {

    override func setUpWithError() throws {
        try super.setUpWithError()
    }

    override func tearDownWithError() throws {
        try super.tearDownWithError()
    }

    func test_viewModel_should_get_data_when_issueManager_get_issues() {
        // given - 뷰모델에 issueManager가 있을 때
        let sut = IssueListViewModel(issueService: IssueServiceSuccessStub())

        // when - 이슈 목록을 요청하면
        sut.requestData()

        // then - 이슈 목록 (데이터)을 가져왔는지 확인.
        // 데이터 갯수가 2개라면 테스트 성공
        XCTAssertEqual(sut.issueViewModels.value?.count, 2)
    }
}
