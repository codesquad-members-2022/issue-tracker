import Foundation


class ReposModel {
    private let service: IssueService
    
    var updated: (([Repository]) -> Void)?
    
    private var optionViewData: [Repository] {
        didSet {
            updated?(optionViewData)
        }
    }
    
    init(service: IssueService) {
        self.service = service
        self.optionViewData = []
    }
    
    var count: Int {
        optionViewData.count
    }
    
    func getViewData(index: Int) -> Repository {
        return optionViewData[index]
    }
    
    func fetchViewData() {
        service.requestRepos() { [weak self] result in
            switch result {
            case .success(let repositoryList):
                self?.optionViewData = repositoryList
                
            case .failure(let error):
                print(error)
            }
        }
    }
}
