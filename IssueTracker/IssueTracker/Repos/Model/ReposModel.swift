import Foundation

// Environment : 디펜던시를 관리하는 객체
class ReposModel {
    
    private let environment: ReposModelEnvironment
    var updated: (([Repository]) -> Void)?
    
    private var ReposList: [Repository] {
        didSet {
            updated?(ReposList)
        }
    }
    
//    init(service: IssueService) {
//        self.service = service
//        self.ReposList = []
//    }
    
    init(environment: ReposModelEnvironment) {
        self.environment = environment
        self.ReposList = []
    }
    
    var count: Int {
        ReposList.count
    }
    
    func getViewData(index: Int) -> Repository {
        return ReposList[index]
    }
    
    func fetchViewData() {
        // (전) service.requestRepos() { ... }
        environment.requestRepos() { [weak self] result in
            switch result {
            case .success(let repositoryList):
                self?.ReposList = repositoryList
                
            case .failure(let error):
                print(error)
            }
        }
    }
}

struct ReposModelEnvironment {
    // ReposModel에 필요한 환경 : IssueService의 requestRepos()뿐이므로, IssueService전체를 넘겨줄 필요가 없다.
    // 기존 service.requestRepos()하던 코드를 보자. (Result<[Repository], IssueError>) -> Void 클로저를 받아쓰고, 리턴값은 없다.
    // 따라서 ReposModel의 환경은  (@escaping ((Result<[Repository], IssueError>)) -> Void) -> Void 가 된다.
    let requestRepos: (@escaping (Result<[Repository], IssueError>) -> Void) -> Void
    
}
