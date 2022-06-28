//
//  IssueListTableViewController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/27.
//

import UIKit
import SnapKit

class IssueListTableViewController: UIViewController, ViewBinding {
    
    typealias NormalCell = IssueListTableViewCell
    typealias SelectableCell = IssueListSelectableTableViewCell
    
    // MARK: - ViewModel
    private lazy var vm = IssueListViewModel { [weak self] param, _ in
        DispatchQueue.main.async {
            guard let index = param as? IndexPath else {
                self?.tableView.reloadData()
                return
            }
            
            self?.tableView.reloadRows(at: [index], with: .fade)
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
            guard let indexes = self.tableView.indexPathsForVisibleRows else {
                self.tableView.reloadData()
                return
            }
            
            self.titleLabel.text = self.viewStatus == .list ? "이슈" : "이슈 선택"
            self.tableView.reloadRows(at: indexes, with: .left)
            
            // 빠른 스크롤에서 reload에 대응하지 못하여 추가하였습니다.
            DispatchQueue.main.asyncAfter(deadline: .now()+0.5) {
                
                var indexes: [IndexPath]?
                
                switch self.viewStatus {
                case .list:
                    let selectableCells = self.tableView.visibleCells.filter({ $0 is SelectableCell })
                    indexes = (selectableCells as? [SelectableCell])?.compactMap({ $0.indexPath })
                case .selection:
                    let selectableCells = self.tableView.visibleCells.filter({ $0 is NormalCell })
                    indexes = (selectableCells as? [NormalCell])?.compactMap({ $0.indexPath })
                }
                
                if let indexes = indexes {
                    self.tableView.reloadRows(at: indexes, with: .left)
                }
            }
        }
    }
    
    // MARK: - ViewController LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        (navigationController as? ViewBindable)?.setVC(self)
        
        setLayout()
        
        tableView.register(NormalCell.self, forCellReuseIdentifier: NormalCell.reuseIdentifier)
        tableView.register(SelectableCell.self, forCellReuseIdentifier: SelectableCell.reuseIdentifier)
        
        tableView.delegate = self
        tableView.dataSource = self
        tableView.rowHeight = 199
        tableView.tableHeaderView = IssueListSearchBar()
    }
    
    func inputViewEvent(_ target: ViewBindable, _ param: Any?) {
        if let cell = target as? NormalCell {
            
            let isSelected = vm.selectList(cell)
            target.receive(isSelected)
            
        } else if let _ = target as? SelectableCell {
            
        } else if (target as? IssueFilterItemSelectViewController) != nil {
            
            print("IssueFilterItemSelectViewController Selected")
            
        } else if (target as? IssueNavigationController) != nil {
            viewStatus = viewStatus == .list ? .selection : .list
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
            $0.leading.trailing.bottom.equalToSuperview()
//            $0.edges.equalToSuperview()
        }
    }
}

extension IssueListTableViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        vm.issueList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        var cell: UITableViewCell?
        
        switch viewStatus {
        case .list:
            cell = tableView.dequeueReusableCell(withIdentifier: NormalCell.reuseIdentifier, for: indexPath)
        case .selection:
            cell = tableView.dequeueReusableCell(withIdentifier: SelectableCell.reuseIdentifier, for: indexPath)
        }
        
        guard let cell = cell else {
            return UITableViewCell()
        }
        
        let data = vm.issueList[indexPath.row]
        (cell as? NormalCell)?.setVC(self)
        (cell as? SelectableCell)?.setVC(self)
        (cell as? NormalCell)?.dto = data
        (cell as? SelectableCell)?.dto = data
        
        return cell
    }
}

extension IssueListTableViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, trailingSwipeActionsConfigurationForRowAt indexPath: IndexPath) -> UISwipeActionsConfiguration? {
        guard viewStatus == .list else {
            return nil
        }
        
        let deleteAction = UIContextualAction(style: .destructive, title: "삭제", handler: { _, _, completionHandler in
            
            guard let cell = tableView.cellForRow(at: indexPath) as? NormalCell, let issue = cell.dto else {
                completionHandler(false)
                return
            }
            
            self.vm.deleteIssue(issue, target: cell)
            completionHandler(true)
        })
        deleteAction.image = UIImage(systemName: "xmark.circle")
        deleteAction.backgroundColor = .red
        
        let closeAction = UIContextualAction(style: .normal, title: "Close") { _, _, completionHandler in
            
            guard let cell = tableView.cellForRow(at: indexPath) as? NormalCell, let issue = cell.dto else {
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
