import UIKit
import SnapKit

struct Margins {
    static let side: CGFloat = 16
}


final class IssueViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationBar()
        setupViews()
    }
    
    private func setupNavigationBar() {
        self.title = "이슈"
        self.navigationController?.navigationBar.prefersLargeTitles = true
        
        let filterButton = createButton(title: "필터", image: UIImage(systemName: "scroll"), action: UIAction(handler: { _ in
            self.touchedFilterButton()
        }))
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: filterButton)
        
        let selectButton = createButton(title: "선택", image: UIImage(systemName: "checkmark.circle"), action: UIAction(handler: { _ in
            self.touchedSelectButton()
        }))
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(customView: selectButton)
    }
    
    private func setupViews() {
        self.view.backgroundColor = .white
        
        collectionView.backgroundColor = .blue
        view.addSubview(collectionView)
        collectionView.snp.makeConstraints { make in
            make.edges.equalTo(self.view).inset(UIEdgeInsets(top: 20, left: Margins.side, bottom: 20, right: Margins.side))
        }
    }
    
    private lazy var collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.register(IssueListCell.self, forCellWithReuseIdentifier: "IssueListCell")
        collectionView.dataSource = self
        collectionView.delegate = self
        return collectionView
    }()
    
    private func createButton(title: String, image: UIImage?, action: UIAction) -> UIButton {
        var configuration = UIButton.Configuration.plain()
        var container = AttributeContainer()
        container.font = UIFont.systemFont(ofSize: 14)
        configuration.attributedTitle = AttributedString(title, attributes: container)
        
        configuration.buttonSize = .small
        configuration.image = image
        configuration.imagePadding = 4
        let button = UIButton(configuration: configuration, primaryAction: action)
        return button
    }
    
    @objc func touchedSelectButton() {
        print("touchedSelectButton")
    }
    
    @objc func touchedFilterButton() {
        print("touchedFilterButton")
    }
}


extension IssueViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return 5
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "IssueListCell", for: indexPath) as? IssueListCell else {
            return UICollectionViewCell()
        }
        cell.backgroundColor = .orange
        return cell
    }
}

extension IssueViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: self.view.frame.width, height: 300)
    }
}


class IssueListCell: UICollectionViewCell {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupViews()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func setupViews() {
        self.addSubview(titleLabel)
        titleLabel.snp.makeConstraints { make in
            make.center.equalToSuperview()
        }
    }
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.text = "제목"
        label.font = .systemFont(ofSize: 22)
        return label
    }()
}
