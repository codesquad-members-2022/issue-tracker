import UIKit


final class IssueViewController: UIViewController {

    private lazy var collectionView: UICollectionView = {
        let layout = UICollectionViewFlowLayout()
        layout.scrollDirection = .vertical
        let collectionView = UICollectionView(frame: .zero, collectionViewLayout: layout)
        collectionView.register(IssueListCell.self, forCellWithReuseIdentifier: IssueListCell.identifier)
        collectionView.dataSource = self
        collectionView.delegate = self
        return collectionView
    }()
    
    private lazy var addButton: UIButton = {
        var configuration = UIButton.Configuration.filled()
//        configuration.image = UIImage(systemName: "plus")
        configuration.baseBackgroundColor = .systemBlue
        configuration.baseForegroundColor = .white
        configuration.contentInsets = NSDirectionalEdgeInsets(top: 5, leading: 5, bottom: 5, trailing: 5)
        configuration.background.cornerRadius = 70
        var button = UIButton(configuration: configuration, primaryAction: UIAction(handler: { [weak self] _ in
            self?.touchedAddButton()
        }))
        button.setImage(UIImage(systemName: "plus"), for: .normal)
        
//        button.layer.cornerRadius = 50 / 2
        
        return button
    }()
    
    private var model: IssueModel
    
    init(model: IssueModel) {
        self.model = model
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: ViewDidLoad
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationBar()
        setupViews()
        model.requestIssue()
        model.updatedIssues = { [weak self] issues in // 모델과 바인딩
            DispatchQueue.main.async { [weak self] in // -> 이걸 그대로 두거나 or AF에서 main이 아닌 다른 큐를 사용하게 설정하거나.
                self?.collectionView.reloadData()
            }
        }
    }
    
    @objc func touchedSelectButton() {
        print("touchedSelectButton")
    }
    
    @objc func touchedFilterButton() {
        print("touchedFilterButton")
        let vc = UIViewController()
        vc.modalPresentationStyle = .fullScreen
        present(vc, animated: true)
    }
    
    @objc func touchedAddButton() {
        self.navigationController?.pushViewController(Container().buildViewController(.newIssue), animated: true)
    }
    
    private func setupNavigationBar() {
        self.title = "이슈"
//        self.navigationController?.navigationBar.prefersLargeTitles = true
        
        let filterButton = createButton(title: "필터", image: UIImage(systemName: "scroll"), action: UIAction(handler: { [weak self] _ in
            self?.touchedFilterButton()
        }))
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: filterButton)
        
        let selectButton = createButton(title: "선택", image: UIImage(systemName: "checkmark.circle"), action: UIAction(handler: { [weak self] _ in
            self?.touchedSelectButton()
        }))
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(customView: selectButton)
    }
    
    private func setupViews() {
        self.view.backgroundColor = .white
        
        view.addSubview(collectionView)
        collectionView.snp.makeConstraints { [weak self] make in
            guard let self = self else {
                return
            }
            make.edges.equalTo(self.view)
        }
        
        view.addSubview(addButton)
        addButton.snp.makeConstraints { [weak self] make in
            guard let self = self else {
                return
            }
            make.bottom.equalTo(self.view).offset(-50)
            make.trailing.equalTo(self.view).offset(-50)
        }
    }
    
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
    
    private func createButton(title: String, action: UIAction) -> UIButton {
        var configuration = UIButton.Configuration.plain()
        var container = AttributeContainer()
        container.font = UIFont.systemFont(ofSize: 14)
        configuration.attributedTitle = AttributedString(title, attributes: container)
        
        configuration.buttonSize = .small
        let button = UIButton(configuration: configuration, primaryAction: action)
        return button
    }
}


extension IssueViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.model.getIssuesCount()
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        guard let cell = collectionView.dequeueReusableCell(withReuseIdentifier: IssueListCell.identifier, for: indexPath) as? IssueListCell,
              let data = model.getIssue(at: indexPath.row) else {
            return UICollectionViewCell()
        }
        cell.updateViews(title: data.title, description: data.body, milestone: data.milestone?.title, labels: data.labels)
        return cell
    }
}

extension IssueViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: collectionView.frame.width, height: 200)
    }
}
