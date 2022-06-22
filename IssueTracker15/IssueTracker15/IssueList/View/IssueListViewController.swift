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

enum IssueListStatus {
    case list
    case selection
    
    mutating func toggle() {
        switch self {
        case .list:
            self = .selection
        case .selection:
            self = .list
        }
    }
}

class IssueListViewController: UIViewController {
    
    // MARK: - typealias(shorten type definition) ref by raywenderlich
    typealias IssueDataSource = UICollectionViewDiffableDataSource<Section, IssueDTO>
    typealias IssueSnapshot = NSDiffableDataSourceSnapshot<Section, IssueDTO>
    
    // MARK: - ViewModel
    private var vm: IssueListViewModel?
    
    // MARK: - IssueCollectionView Properties
    private var issueList = Array(repeating: IssueDTO.empty, count: 25) {
        didSet {
            self.applySnapshot()
        }
    }
    
    private var currentViewState: IssueListStatus = .list {
        willSet {
            switch currentViewState {
            case .list:
                print("이슈 리스트 기본화면을 보여주세요.")
            case .selection:
                print("이슈 리스트 선택화면을 보여주세요.")
            }
        }
    }
    
    // MARK: - Navigation Bar Actions
    
    private var filterBarButtonAction: UIAction {
        UIAction(
            title: "필터",
            image: UIImage.filterButtonImage,
            identifier: nil,
            discoverabilityTitle: "Filter Issue List",
            attributes: [],
            state: .on,
            handler: { _ in
                let filterVC = IssueFilterItemSelectViewController()
                filterVC.setVC(self)
                self.show(filterVC, sender: nil)
            }
        )
    }
    
    private var selectIssueBarButtonAction: UIAction {
        UIAction(
            title: "선택",
            image: UIImage.checkButtonImage,
            identifier: nil,
            discoverabilityTitle: "Select Issue List",
            attributes: [],
            state: .on,
            handler: { _ in
                self.currentViewState.toggle()
            }
        )
    }
    
    // MARK: - Initialize IssueCollectionView
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
                cell?.setVC(self)
                return cell
            }
        )
    }()
    
    // MARK: - ViewController LifeCycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: UIButton(type: .system, primaryAction: filterBarButtonAction))
        navigationItem.rightBarButtonItem = UIBarButtonItem(customView: UIButton(type: .system, primaryAction: selectIssueBarButtonAction))
        
        vm = IssueListViewModel { param, bindable in
            print("Actions move to Issue Detail ViewController using \(String(describing: param)), \(bindable)")
        }
        
        view.addSubview(issueListCollectionView)
        issueListCollectionView.snp.makeConstraints {
            $0.edges.equalToSuperview()
        }
        
        applySnapshot()
    }
    
    // MARK: - IssueViewController Apply/Reload
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

extension IssueListViewController: ViewBinding {
    func inputViewEvent(_ target: ViewBindable, _ param: Any?) {
        if (target as? IssueListCell) != nil {
            if currentViewState == .selection, let state = param as? CheckButtonSelected {
                var mutateState = state
                mutateState.toggle()
                target.receive(mutateState)
            } else {
                print("IssueListCell Selected")
            }
        } else if (target as? IssueFilterItemSelectViewController) != nil {
            print("IssueFilterItemSelectViewController Showed")
        }
    }
}

// MARK: - CollectionView Delegate
extension IssueListViewController: UICollectionViewDelegate {
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
