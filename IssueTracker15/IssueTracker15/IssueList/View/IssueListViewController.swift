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
    
    private var issueList = Array(repeating: IssueDTO.empty, count: 25) {
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
        collectionView.register(IssueListCell.self,
                                forCellWithReuseIdentifier: IssueListCell.reuseIdentifier)
        collectionView.register(IssueListHeaderReusableView.self,
                                forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader,
                                withReuseIdentifier: IssueListHeaderReusableView.reuseIdentifier)
        collectionView.register(IssueListFooterReusableView.self,
                                forSupplementaryViewOfKind: UICollectionView.elementKindSectionFooter,
                                withReuseIdentifier: IssueListFooterReusableView.reuseIdentifier)
        collectionView.collectionViewLayout = UICollectionViewCompositionalLayout(sectionProvider: { (_, _) -> NSCollectionLayoutSection? in
            let size = NSCollectionLayoutSize(
                widthDimension: .fractionalWidth(1),
                heightDimension: .absolute(200)
            )
            let item = NSCollectionLayoutItem(layoutSize: size)
            let section = NSCollectionLayoutSection(group: NSCollectionLayoutGroup.vertical(layoutSize: size, subitem: item, count: 1))
            
            let sectionHeader = NSCollectionLayoutBoundarySupplementaryItem(
                layoutSize: NSCollectionLayoutSize(
                    widthDimension: .fractionalWidth(1),
                    heightDimension: .estimated(55)
                ),
                elementKind: UICollectionView.elementKindSectionHeader,
                alignment: .top
            )
            let sectionFooter = NSCollectionLayoutBoundarySupplementaryItem(
                layoutSize: NSCollectionLayoutSize(
                    widthDimension: .fractionalWidth(1),
                    heightDimension: .estimated(60)
                ),
                elementKind: UICollectionView.elementKindSectionFooter,
                alignment: .bottom
            )
            section.boundarySupplementaryItems = [sectionHeader, sectionFooter]
            
            return section
        })
        return collectionView
    }()
    
    private lazy var issueDataSource: DataSource = {
        DataSource(
            collectionView: issueListCollectionView,
            cellProvider: { (collectionView, indexPath, issue) -> UICollectionViewCell? in
                let cell = collectionView.dequeueReusableCell(withReuseIdentifier: IssueListCell.reuseIdentifier, for: indexPath) as? IssueListCell
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
        
        let leftFilterButton = UIButton(type: .system)
        leftFilterButton.setTitle("필터", for: .normal)
        leftFilterButton.setImage(UIImage(systemName: "line.3.horizontal.decrease"), for: .normal)
        leftFilterButton.sizeToFit()
        leftFilterButton.addAction(queryAction, for: .touchUpInside)
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: leftFilterButton)
        
        let rightSelectButton = UIButton(type: .system)
        rightSelectButton.setImage(UIImage(systemName: "checkmark.circle"), for: .normal)
        rightSelectButton.setTitle("선택", for: .normal)
        rightSelectButton.sizeToFit()
        rightSelectButton.addAction(checkSelectAction, for: .touchUpInside)
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: rightSelectButton)
        
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
        var items = issueList
        
        for i in items.indices {
            items[i].id = i
        }
        
        issueSnapshot.appendSections([Section.main])
        issueSnapshot.appendItems(items)
        issueDataSource.supplementaryViewProvider = { collectionView, kind, indexPath in
            switch kind {
            case UICollectionView.elementKindSectionHeader:
                return collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: IssueListHeaderReusableView.reuseIdentifier, for: indexPath) as? IssueListHeaderReusableView
            case UICollectionView.elementKindSectionFooter:
                return collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: IssueListFooterReusableView.reuseIdentifier, for: indexPath) as? IssueListFooterReusableView
            default:
                return nil
            }
        }
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
