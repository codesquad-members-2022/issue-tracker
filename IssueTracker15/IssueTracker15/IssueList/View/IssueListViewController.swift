//
//  IssueListViewController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import UIKit

class IssueListViewController: UIViewController {
    
    typealias DataSource = UICollectionViewDiffableDataSource<Section, IssueDTO>
    typealias Snapshot = NSDiffableDataSourceSnapshot<Section, IssueDTO>
    
    private var vm: IssueListViewModel?
    
    private var issueList = [IssueDTO]() {
        didSet {
            self.applySnapshot()
        }
    }
    
    private lazy var issueListCollectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.delegate = self
        collectionView.isScrollEnabled = true
        collectionView.register(IssueListCell.self, forCellWithReuseIdentifier: IssueListCell.reuseIdentifier)
        return collectionView
    }()
    
    private lazy var issueDataSource: DataSource = {
        DataSource(
            collectionView: issueListCollectionView,
            cellProvider: { (collectionView, indexPath, issue) -> UICollectionViewCell? in
                let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "", for: indexPath) as? IssueListCell
                cell?.issueDTO = issue
                return cell
            }
        )
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let queryAction = UIAction(title: "query") { _ in
            print("QueryActionButton TouchUpInside")
        }
        let checkSelectAction = UIAction(title: "checkSelect") { _ in
            print("CheckSelectButton TouchUpInside")
        }
        
        navigationItem.leftBarButtonItem = UIBarButtonItem(title: "필터", image: UIImage(systemName: "line.3.horizontal.decrease"), primaryAction: queryAction, menu: .none)
        navigationItem.rightBarButtonItem = UIBarButtonItem(title: "선택", image: UIImage(systemName: "checkmark.circle"), primaryAction: checkSelectAction, menu: .none)
        
//        vm = IssueListViewModel { param, bindable in }
        vm = IssueListViewModel()
        
        view.addSubview(issueListCollectionView)
        issueListCollectionView.snp.makeConstraints {
            $0.edges.equalToSuperview()
        }
        
        applySnapshot()
    }
    
    private func applySnapshot(animatingDifferences: Bool = true) {
        var issueSnapshot = Snapshot()
        issueSnapshot.appendSections([Section.main])
        issueSnapshot.appendItems([IssueDTO]())
        issueDataSource.apply(issueSnapshot, animatingDifferences: animatingDifferences)
    }
}

extension IssueListViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
//        vm?.request(, param: issueDataSource.itemIdentifier(for: indexPath))
        vm?.request(param: issueDataSource.itemIdentifier(for: indexPath))
    }
}

enum Section {
    case main
}
