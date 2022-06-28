//
//  IssueListViewController.swift
//  IssueTracker
//
//  Created by 최예주 on 2022/06/14.
//

import UIKit
import SnapKit

final class IssueListViewController: UIViewController {

    var viewModel: IssueListViewModel

    init(viewModel: IssueListViewModel) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }

    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("\(#function) has not been implemented")
    }

    private var collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        collectionView.register(IssueCell.self, forCellWithReuseIdentifier: IssueCell.reuseIdentifier)
        return collectionView
    }()

    private lazy var filterButton: UIBarButtonItem = {
        let button = UIButton()
        button.setImage(UIImage(named: "filterIcon"), for: .normal)
        button.frame = CGRect(x: 0, y: 0, width: 70, height: 30)
        button.setTitle("필터", for: .normal)
        button.setTitleColor(.systemBlue, for: .normal)
        button.titleEdgeInsets = UIEdgeInsets(top: 0, left: 10, bottom: 0, right: 0)
        button.addTarget(self, action: #selector(filterButtonPressed(_:)), for: .touchUpInside)

        let barbutton = UIBarButtonItem(customView: button)
        return barbutton
    }()

    private lazy var selectButton: UIBarButtonItem = {
        let button = UIButton()
        button.setTitle("선택", for: .normal)
        button.setImage(UIImage(named: "selectIcon"), for: .normal)
        button.frame = CGRect(x: 0, y: 0, width: 70, height: 30)
        button.setTitleColor(.systemBlue, for: .normal)
        button.titleEdgeInsets = UIEdgeInsets(top: 0, left: 10, bottom: 0, right: 0)
        button.addTarget(self, action: #selector(selectButtonPressed(_:)), for: .touchUpInside)

        let barbutton = UIBarButtonItem(customView: button)
        return barbutton
    }()

    override func viewDidLoad() {
        super.viewDidLoad()
        collectionView.dataSource = self
        collectionView.delegate = self
        self.view.addSubview(collectionView)
        setLayout()
        setNavigationController()
        bind()

    }

    private func setLayout() {
        collectionView.snp.makeConstraints { make in
            make.edges.equalToSuperview()
        }
    }

    private func bind() {
        viewModel.issueList.bind { [weak self] issueList in
            guard let self = self else { return }
            DispatchQueue.main.async {
                self.collectionView.reloadData()
            }
        }
    }

    private func setNavigationController() {
        self.navigationItem.setLeftBarButton(filterButton, animated: true)
        self.navigationItem.setRightBarButton(selectButton, animated: true)
    }

    @objc private func filterButtonPressed(_ sender: Any) {
        // TODO: filter Modal present
    }

    @objc private func selectButtonPressed(_ sender: Any) {
        // TODO: select Issue Logic
    }
}

extension IssueListViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return viewModel.numberOfItemsInSection
    }

    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: IssueCell.reuseIdentifier, for: indexPath) as? IssueCell else { return UICollectionViewCell() }
        cell.titleLabel.text = viewModel.issueList.value[indexPath.item].title
        return cell
    }

}

extension IssueListViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let size = CGSize(width: collectionView.frame.width, height: 199)
        return size
    }
}
