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

class IssueListCollectionViewController: UIViewController {
    
    // MARK: - typealias(shorten type definition) ref by raywenderlich
    typealias IssueDataSource = UICollectionViewDiffableDataSource<Section, IssueDTO>
    typealias IssueSnapshot = NSDiffableDataSourceSnapshot<Section, IssueDTO>
    
    // MARK: - ViewModel
    private var vm: IssueListViewModel?
    
    // MARK: - IssueCollectionView Properties
    private var issueNavigationController: IssueNavigationController? {
        return navigationController as? IssueNavigationController
    }
    
    // MARK: - Initialize IssueCollectionView
    private lazy var issueListCollectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.delegate = self
        collectionView.register(IssueListCollectioViewCell.self,
                                forCellWithReuseIdentifier: IssueListCollectioViewCell.reuseIdentifier) // Register Cell
        collectionView.register(IssueListCollectionHeaderReusableView.self,
                                forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader,
                                withReuseIdentifier: IssueListCollectionHeaderReusableView.reuseIdentifier) // Register Header
        collectionView.register(IssueListCollectionFooterReusableView.self,
                                forSupplementaryViewOfKind: UICollectionView.elementKindSectionFooter,
                                withReuseIdentifier: IssueListCollectionFooterReusableView.reuseIdentifier) // Register Footer
        collectionView.collectionViewLayout = issueListViewLayout()
        
        return collectionView
    }()
    
    private var issueListViewLayout: () -> UICollectionViewCompositionalLayout = {
        
        var listConfig = UICollectionLayoutListConfiguration(appearance: .insetGrouped)
        listConfig.itemSeparatorHandler = { _, _ in
            var sectionConfig = UIListSeparatorConfiguration(listAppearance: UICollectionLayoutListConfiguration.Appearance.plain)
            
            sectionConfig.topSeparatorInsets = NSDirectionalEdgeInsets(top: 5, leading: 5, bottom: 5, trailing: 5)
            sectionConfig.bottomSeparatorInsets = NSDirectionalEdgeInsets(top: 5, leading: 5, bottom: 5, trailing: 5)
            sectionConfig.bottomSeparatorVisibility = .visible
            
            return sectionConfig
        }
        // (_ itemIndexPath: IndexPath, _ sectionSeparatorConfiguration: UIListSeparatorConfiguration) -> UIListSeparatorConfiguration
        
        return UICollectionViewCompositionalLayout.list(using: listConfig)
    }
    
    private lazy var issueDataSource: IssueDataSource = {
        IssueDataSource(
            collectionView: issueListCollectionView,
            cellProvider: { (collectionView, indexPath, issue) -> UICollectionViewCell? in
                let cell = collectionView.dequeueReusableCell(withReuseIdentifier: IssueListCollectioViewCell.reuseIdentifier, for: indexPath) as? IssueListCollectioViewCell
                cell?.issueDTO = issue
                cell?.setVC(self)
                return cell
            }
        )
    }()
    
    // MARK: - ViewController LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if let navigationBinding = navigationController as? ViewBindable {
            navigationBinding.setVC(self)
        }
        
        vm = IssueListViewModel { [weak self] _, _ in
            self?.applySnapshot()
        }
        
        view.addSubview(issueListCollectionView)
        issueListCollectionView.snp.makeConstraints {
            $0.edges.equalToSuperview()
        }
        
        applySnapshot()
    }
    
    // MARK: - IssueViewController Apply/Reload
    private func applySnapshot(animatingDifferences: Bool = true) {
        guard let items = vm?.issueList else { return }
        
        var issueSnapshot = IssueSnapshot()
        
        issueSnapshot.appendSections([Section.main])
        issueSnapshot.appendItems(items)
        
        issueDataSource.supplementaryViewProvider = { collectionView, kind, indexPath in
            switch kind {
            case UICollectionView.elementKindSectionHeader:
                return collectionView.dequeueReusableSupplementaryView(
                    ofKind: kind,
                    withReuseIdentifier: IssueListCollectionHeaderReusableView.reuseIdentifier,
                    for: indexPath) as? IssueListCollectionHeaderReusableView
            case UICollectionView.elementKindSectionFooter:
                return collectionView.dequeueReusableSupplementaryView(
                    ofKind: kind,
                    withReuseIdentifier: IssueListCollectionFooterReusableView.reuseIdentifier,
                    for: indexPath) as? IssueListCollectionFooterReusableView
            default:
                return nil
            }
        }
        
        issueDataSource.apply(issueSnapshot, animatingDifferences: animatingDifferences)
    }
}

extension IssueListCollectionViewController: ViewBinding {
    
    func inputViewEvent(_ target: ViewBindable, _ param: Any?) {
        
        if let cell = target as? IssueListTableViewCell {
            
            let isSelected = vm?.selectList(cell) ?? false
            target.receive(isSelected)
            
        } else if (target as? IssueFilterItemSelectViewController) != nil {
            
            print("IssueFilterItemSelectViewController Showed")
            
        } else if (target as? IssueNavigationController) != nil {
            
            let filterVC = IssueFilterItemSelectViewController()
            filterVC.setVC(self)
            present(filterVC, animated: true)
        }
    }
}

// MARK: - CollectionView Delegate
extension IssueListCollectionViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        print("IssueListCell Delegate for Issue Detail")
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
