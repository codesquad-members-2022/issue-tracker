import UIKit
import SnapKit

struct Margins {
    static let side: CGFloat = 16
}


final class IssueViewController: UIViewController {

    struct Dummy {
        let title: String
        let description: String
        let milestone: String
        let label: String
    }
    
    private let dummy: [Dummy] = [
        .init(title: "iOS 이슈 트래커 개발", description: "개발일정: 8월 3일부터 9월 2일까지", milestone: "마스터즈 코스", label: "Documentation"),
        .init(title: "다마고치 게임 개발", description: "iOS, Watch 를 지원하는 게임을 개발한다", milestone: "개인 프로젝트", label: "Feature"),
        .init(title: "타이머 앱의 시간 오류", description: "타이머 앱에서 잘못된 시간을 화면에 보여주고 있습니다", milestone: "개인 프로젝트", label: "bug"),
        .init(title: "PR 보내기", description: "iOS 이슈 트래커의 코드리뷰를 받기위해 PR 보내기", milestone: "마스터즈 코스", label: "Documentation")
    ]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationBar()
        setupViews()
    }
    
    @objc func touchedSelectButton() {
        print("touchedSelectButton")
    }
    
    @objc func touchedFilterButton() {
        print("touchedFilterButton")
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
            make.edges.equalTo(self.view)
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
}


extension IssueViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return dummy.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "IssueListCell", for: indexPath) as? IssueListCell else {
            return UICollectionViewCell()
        }
        cell.backgroundColor = .orange
        let data = dummy[indexPath.row]
        cell.updateViews(title: data.title, description: data.description, milestone: data.milestone, label: data.label)
        return cell
    }
}

extension IssueViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: collectionView.frame.width, height: 200)
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
    
    func updateViews(title: String, description: String, milestone: String, label: String) {
        self.titleLabel.text = title
        self.descriptionLabel.text = description
        self.milestoneLabel.text = milestone
        self.labelNameButton.titleLabel?.text = label
    }
    
    private func setupViews() {
        let stackView = UIStackView(arrangedSubviews: [titleLabel, descriptionLabel, milestoneLabel, labelNameButton])
        stackView.alignment = .leading
        stackView.spacing = 16
        stackView.distribution = .equalSpacing
        stackView.axis = .vertical
        
        self.addSubview(stackView)
        stackView.snp.makeConstraints { make in
            make.edges.equalTo(self).inset(UIEdgeInsets(top: 24, left: Margins.side, bottom: 24, right: Margins.side))
        }
    }
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.text = "제목"
        label.font = .systemFont(ofSize: 22)
        return label
    }()
    
    private let descriptionLabel: UILabel = {
        let label = UILabel()
        label.text = "description"
        label.textColor = .systemGray
        return label
    }()
    
    private let milestoneLabel: UILabel = {
        let label = UILabel()
        label.text = "milestone"
        label.textColor = .systemGray
        return label
    }()
    
    private let labelNameButton: UIButton = {
        var configuration = UIButton.Configuration.filled()
        configuration.buttonSize = .medium
        configuration.cornerStyle = .capsule
        configuration.title = "label name"
        let button = UIButton(configuration: configuration, primaryAction: nil)
        return button
    }()
}
