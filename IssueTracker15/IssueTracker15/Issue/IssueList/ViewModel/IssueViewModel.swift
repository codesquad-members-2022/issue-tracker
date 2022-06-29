//
//  IssueListViewModel.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import Foundation

enum IssueAction {
    case getIssue
    case selectIssue
    case closeIssue
    case deleteIssue
}

class IssueListViewModel: CommonViewModel {
    
    private lazy var listUseCase = UseCaseContainer.shared.resolve(type: IssueListUseCase.self)
    private lazy var modifyUseCase = UseCaseContainer.shared.resolve(type: IssueModifyUseCase.self)
    
    private(set) var issueList = [IssueDTO]()
    
    var output: (Any?, ViewBindable) -> Void

    init(_ output: @escaping (Any?, ViewBindable) -> Void) {
        self.output = output
        
        UseCaseContainer.shared.regist(type: IssueListUseCase.self) {
            return IssueListUseCase()
        }
        UseCaseContainer.shared.regist(type: IssueModifyUseCase.self) {
            return IssueModifyUseCase()
        }
    }

    func request(_ bindable: ViewBindable, param: Any?) { // Modify, Select,
        guard let param = param as? IssueAction else {
            return
        }

        switch param {
        case .getIssue: getIssues(bindable)
        case .selectIssue: selectList(bindable)
//            case .closeIssue: closeIssue(bindable)
//            case .deleteIssue: deleteIssue(bindable)
        case .closeIssue, .deleteIssue: updateIssue(bindable, param: param)
        }
    }
    
    private func getIssues(_ tableView: ViewBindable) {
        listUseCase?.request({ [weak self] list in
            if let list = list as? [IssueDTO] {
                self?.issueList = list
                self?.output(list, tableView)
            }
        })
    }
    
    private func selectList(_ cell: ViewBindable) {
        guard
            let cell = cell as? IssueListTableViewCell,
            let index = self.issueList.firstIndex(where: { $0.id == cell.dto?.id })
        else {
            return
        }
        
        issueList[index].isSelected.toggle()
        output(cell.indexPath, cell)
    }
    
//    func closeIssue(_ target: ViewBindable) {
//        guard
//            let cell = target as? IssueListTableViewCell,
//            let index = issueList.firstIndex(where: { $0.id == cell.dto?.id })
//        else {
//            return
//        }
//
//        modifyUseCase?.request(
//            { [weak self] _ in
//                self?.issueList[index].closed_at = Date().ISO8601Format()
//                self?.output(cell.indexPath, target)
//            }
//        )
//    }
//
//    func deleteIssue(_ target: ViewBindable) {
//        guard
//            let cell = target as? IssueListTableViewCell,
//            let index = issueList.firstIndex(where: { $0.id == cell.dto?.id })
//        else {
//            return
//        }
//
//        modifyUseCase?.request(
//            { [weak self] _ in
//                self?.issueList.remove(at: index)
//                (target as? IssueListTableViewCell)?.willBeDelete = true
//                self?.output(cell.indexPath, target)
//            }
//        )
//    }
    
    private func updateIssue(_ target: ViewBindable, param: IssueAction) {
        guard let cell = target as? IssueListTableViewCell, let useCase = modifyUseCase as? IssueModifyUseCase else {
            return
        }
        
        if param == .closeIssue {
            if let index = issueList.firstIndex(where: { $0.id == cell.dto?.id }) {
                issueList.remove(at: index)
                (target as? IssueListTableViewCell)?.willBeDelete = true
            }
            return
        }
        
        useCase.param = cell.dto
        useCase.request({ [weak self] _ in
            
            defer {
                self?.output(cell.indexPath, target)
            }
            
            guard let index = self?.issueList.firstIndex(where: { $0.id == cell.dto?.id }) else {
                return
            }
            
            if param == .closeIssue {
                self?.issueList[index].closed_at = Date().ISO8601Format()
            }
        })
    }
}

private extension DispatchTime {
    static var randomFive: DispatchTime {
        DispatchTime(uptimeNanoseconds: UInt64(Int.random(in: 1...5)))
    }
}
