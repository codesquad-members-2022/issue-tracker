import UIKit
import SnapKit


class IssueViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        self.navigationController?.navigationBar.prefersLargeTitles = true
        self.title = "이슈"
        self.view.backgroundColor = .white
        
        let filterButton = createButton(title: "필터", image: UIImage(systemName: "scroll"), action: UIAction(handler: { _ in
            self.touchedFilterButton()
        }))
        navigationItem.leftBarButtonItem = UIBarButtonItem(customView: filterButton)
        
        let selectButton = createButton(title: "선택", image: UIImage(systemName: "checkmark.circle"), action: UIAction(handler: { _ in
            self.touchedSelectButton()
        }))
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(customView: selectButton)
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
    
    @objc func touchedSelectButton() {
        print("touchedSelectButton")
    }
    
    @objc func touchedFilterButton() {
        print("touchedFilterButton")
    }
    
}


class IssueListCell: UICollectionViewCell {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
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
