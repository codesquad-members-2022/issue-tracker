//
//  IssueListTableViewController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/27.
//

import UIKit
import SnapKit

class IssueListTableViewController: UIViewController, ViewBinding {
    
    // Cell의 타입 이름이 길어서 typealias 를 이용해 직관적으로 변경하였습니다.
    typealias CELL = IssueListTableViewCell
    
    // MARK: - ViewModel
    private lazy var vm = IssueListViewModel { param, target in
        DispatchQueue.main.async { [weak self] in
            guard let index = param as? IndexPath else {
                self?.tableView.reloadData()
                return
            }
            
            if let cell = target as? CELL, cell.willBeDelete {
                self?.tableView.deleteRows(at: [index], with: .fade)
            } else {
                self?.tableView.reloadRows(at: [index], with: .fade)
            }
        }
    }
    
    private var titleLabel: UILabel = {
        let label = UILabel()
        label.font = label.font.withSize(34)
        label.text = "이슈"
        return label
    }()
    
    // MARK: - IssueTableView Properties
    
    private var tableView = UITableView()
    
    private var viewStatus: IssueListStatus = .list {
        didSet {
            self.titleLabel.text = self.viewStatus == .list ? "이슈" : "이슈 선택"
            self.tableView.reloadData()
        }
    }
    
    // MARK: - ViewController LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        (navigationController as? ViewBindable)?.setVC(self)
        
        setLayout()
        
        tableView.register(CELL.self, forCellReuseIdentifier: CELL.reuseIdentifier)
        
        tableView.delegate = self
        tableView.dataSource = self
        tableView.rowHeight = 199
        tableView.tableHeaderView = IssueListSearchBar()
    }
    
    func inputViewEvent(_ target: ViewBindable, _ param: Any?) {
        if let cell = target as? CELL {
            
            let isSelected = vm.selectList(cell)
            target.receive(isSelected)
            
        } else if (target as? IssueFilterItemSelectViewController) != nil {
            
            print("IssueFilterItemSelectViewController Selected")
            
        } else if (target as? IssueNavigationController) != nil, let button = param as? UIBarButtonItem {
            let navigationItem = navigationController?.navigationBar.topItem
            
            if button == navigationItem?.leftBarButtonItem {
                present(IssueFilterItemSelectViewController(), animated: true)
            } else if button == navigationItem?.rightBarButtonItem {
                viewStatus = viewStatus == .list ? .selection : .list
            }
        }
    }
    
    private func setLayout() {
        view.addSubview(titleLabel)
        view.addSubview(tableView)
        
        titleLabel.snp.makeConstraints {
            $0.top.equalTo(view.safeAreaLayoutGuide.snp.top)
            $0.leading.equalToSuperview().offset(16)
            $0.trailing.equalToSuperview().inset(16)
            $0.height.equalTo(55)
        }
        
        tableView.snp.makeConstraints {
            $0.top.equalTo(self.titleLabel.snp.bottom)
            $0.leading.trailing.equalToSuperview()
            $0.bottom.equalTo(view.safeAreaLayoutGuide.snp.bottom)
        }
    }
}

extension IssueListTableViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        vm.issueList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CELL.reuseIdentifier, for: indexPath) as? CELL else {
            return UITableViewCell()
        }
        
        let data = vm.issueList[indexPath.row]
        
        cell.showButton(viewStatus != .list)
        cell.setVC(self)
        cell.dto = data
        
        return cell
    }
}

extension IssueListTableViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        guard viewStatus == .list else {
            return nil
        }
        
        let deleteAction = UIContextualAction(style: .destructive, title: "삭제", handler: { _, _, completionHandler in
            
            guard let cell = tableView.cellForRow(at: indexPath) as? CELL, let issue = cell.dto else {
                completionHandler(false)
                return
            }
            
            self.vm.deleteIssue(issue, target: cell)
            completionHandler(true)
        })
        deleteAction.image = UIImage(systemName: "xmark.circle")
        deleteAction.backgroundColor = .red
        
        let closeAction = UIContextualAction(style: .normal, title: "닫기") { _, _, completionHandler in
            
            guard let cell = tableView.cellForRow(at: indexPath) as? CELL, let issue = cell.dto else {
                completionHandler(false)
                return
            }
            
            self.vm.closeIssue(issue, target: cell)
            completionHandler(true)
        }
        closeAction.image = UIImage(systemName: "archivebox")
        closeAction.backgroundColor = .purple
        
        return UISwipeActionsConfiguration(actions: [closeAction, deleteAction])
    }
}

extension IssueListTableViewController: UISearchResultsUpdating {
    func updateSearchResults(for searchController: UISearchController) {
        
    }
}

private extension UITableViewCell {
    static var reuseIdentifier: String {
        return String(describing: Self.self)
    }
}
