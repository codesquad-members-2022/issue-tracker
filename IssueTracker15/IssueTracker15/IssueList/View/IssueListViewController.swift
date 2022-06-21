//
//  IssueListViewController.swift
//  IssueTracker15
//
//  Created by 백상휘 on 2022/06/20.
//

import UIKit

enum Section {
    case main
}

class IssueListViewController: UIViewController {
    
    // MARK: - typealias(shorten type definition) ref by raywenderlich
    typealias IssueDataSource = UICollectionViewDiffableDataSource<Section, IssueDTO>
    typealias IssueSnapshot = NSDiffableDataSourceSnapshot<Section, IssueDTO>
    
    // MARK: - ViewModel
    private var vm: IssueListViewModel?
    
    // MARK: - CollectionView Data
    private var issueList = Array(repeating: IssueDTO.empty, count: 25) {
        didSet {
            self.applySnapshot()
        }
    }
    
    // MARK: - Navigation Bar Buttons
    private var leftFilterButton: () -> UIButton = {
        let button = UIButton(type: .system)
        button.setTitle("필터", for: .normal)
        button.setImage(UIImage(systemName: "line.3.horizontal.decrease"), for: .normal)
        button.sizeToFit()
        button.addAction(
            UIAction(title: "query") { _ in
                print("QueryActionButton TouchUpInside")
            },
            for: .touchUpInside
        )
        return button
    }
    
    private var rightSelectButton: () -> UIButton = {
        let button = UIButton(type: .system)
        button.setImage(UIImage(systemName: "checkmark.circle"), for: .normal)
        button.setTitle("선택", for: .normal)
        button.sizeToFit()
        button.addAction(
            UIAction(title: "select") { _ in
                print("SelectActionButton TouchUpInside")
            },
            for: .touchUpInside
        )
        return button
    }
    
    // MARK: - Initialize CollectionView
    private lazy var issueListCollectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.delegate = self
        collectionView.register(IssueListCell.self,
                                forCellWithReuseIdentifier: IssueListCell.reuseIdentifier) // Register Cell
        collectionView.register(IssueListHeaderReusableView.self,
                                forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader,
                                withReuseIdentifier: IssueListHeaderReusableView.reuseIdentifier) // Register Header
        collectionView.register(IssueListFooterReusableView.self,
                                forSupplementaryViewOfKind: UICollectionView.elementKindSectionFooter,
                                withReuseIdentifier: IssueListFooterReusableView.reuseIdentifier) // Register Footer
        collectionView.collectionViewLayout = issueListViewLayout()
        
        return collectionView
    }()
    
    private var issueListViewLayout: () -> UICollectionViewCompositionalLayout = {
        
        UICollectionViewCompositionalLayout(sectionProvider: { (_, _) -> NSCollectionLayoutSection? in
            let item = NSCollectionLayoutItem(layoutSize: .cellSize)
            let section = NSCollectionLayoutSection(// Definition Layout Cells in Section
                group: NSCollectionLayoutGroup.vertical(layoutSize: .cellSize, subitem: item, count: 1)
            )
            
            section.boundarySupplementaryItems = [
                NSCollectionLayoutBoundarySupplementaryItem( // Definition Layout Header
                    layoutSize: .headerSize,
                    elementKind: UICollectionView.elementKindSectionHeader,
                    alignment: .top
                ),
                NSCollectionLayoutBoundarySupplementaryItem( // Definition Layout Footer
                    layoutSize: .footerSize,
                    elementKind: UICollectionView.elementKindSectionFooter,
                    alignment: .bottom
                )
            ]
            
            return section
        })
    }
    
    private lazy var issueDataSource: IssueDataSource = {
        IssueDataSource(
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
        
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: leftFilterButton())
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: rightSelectButton())
        
        vm = IssueListViewModel()
        
        view.addSubview(issueListCollectionView)
        issueListCollectionView.snp.makeConstraints {
            $0.edges.equalToSuperview()
        }
        
        applySnapshot()
    }
    
    private func applySnapshot(animatingDifferences: Bool = true) {
        var issueSnapshot = IssueSnapshot()
        var items = issueList
        
        for i in items.indices { // issueList items are Hashable with id property.
            items[i].id = i
        }
        
        issueSnapshot.appendSections([Section.main])
        issueSnapshot.appendItems(items)
        issueDataSource.supplementaryViewProvider = { collectionView, kind, indexPath in
            switch kind {
            case UICollectionView.elementKindSectionHeader:
                return collectionView.dequeueReusableSupplementaryView(
                    ofKind: kind,
                    withReuseIdentifier: IssueListHeaderReusableView.reuseIdentifier,
                    for: indexPath) as? IssueListHeaderReusableView
            case UICollectionView.elementKindSectionFooter:
                return collectionView.dequeueReusableSupplementaryView(
                    ofKind: kind,
                    withReuseIdentifier: IssueListFooterReusableView.reuseIdentifier,
                    for: indexPath) as? IssueListFooterReusableView
            default:
                return nil
            }
        }
        issueDataSource.apply(issueSnapshot, animatingDifferences: animatingDifferences)
    }
}

// MARK: - CollectionView Delegate
extension IssueListViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        vm?.request(param: issueDataSource.itemIdentifier(for: indexPath))
    }
}

// MARK: - NSCollectionLayoutSize extension private
private extension NSCollectionLayoutSize {
    static var headerSize: NSCollectionLayoutSize {
        NSCollectionLayoutSize(
            widthDimension: .fractionalWidth(1), heightDimension: .estimated(55)
        )
    }
    
    static var footerSize: NSCollectionLayoutSize {
        NSCollectionLayoutSize(
            widthDimension: .fractionalWidth(1), heightDimension: .estimated(55)
        )
    }
    
    static var cellSize: NSCollectionLayoutSize {
        NSCollectionLayoutSize(
            widthDimension: .fractionalWidth(1), heightDimension: .absolute(200)
        )
    }
}

// MARK: - Definition reuseIdentifiers
private extension UICollectionReusableView {
    static var reuseIdentifier: String {
        String(describing: Self.self)
    }
}
