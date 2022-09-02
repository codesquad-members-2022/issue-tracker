import UIKit
import SnapKit

protocol ReposViewControllerDelegate {
    func showIssue(didSelectRowAt indexPath: IndexPath)
}

class ReposViewController: UIViewController {
    
    private let model: ReposModel
    private let tableViewCellIdentifier = "tableViewCellIdentifier"
    
    var delegate: ReposViewControllerDelegate?
    
    init(model: ReposModel) {
        self.model = model
        super.init(nibName: nil, bundle: nil)
    }

    required convenience init?(coder: NSCoder) {
        self.init(model: ReposModel(environment: .init(requestRepos: { completion in
        })))
    }
    
    deinit {
        print("-- \(type(of: self)) is deinited")
    }
    
    // 뷰 : 이벤트를 발생시키는 공간
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        setupViews()
        
        model.fetchViewData()
        model.updated = { [weak self] repos in
            DispatchQueue.main.async {
                self?.reloadTableView()
            }
        }
    }
    
    func reloadTableView() {
        tableView.reloadData()
    }
    
    func setupViews() {
        print("ReposVC - setupViews")
        view.addSubview(tableView)
        tableView.snp.makeConstraints { make in
            make.edges.equalToSuperview()
        }
    }
    
    private lazy var tableView: UITableView = {
        let tableView = UITableView()
        tableView.register(UITableViewCell.self,
                           forCellReuseIdentifier: tableViewCellIdentifier)
        tableView.delegate = self
        tableView.dataSource = self
        return tableView
    }()

}

extension ReposViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print("셀 선택 : \(indexPath)")
        print(self.delegate)
        self.delegate?.showIssue(didSelectRowAt: indexPath)
    }
}

extension ReposViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return model.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
//        guard let options = options else {
//            return UITableViewCell()
//        }
        let data = model.getViewData(index: indexPath.row)
        let cell = tableView.dequeueReusableCell(withIdentifier: tableViewCellIdentifier,
                                                 for: indexPath)
        var content = cell.defaultContentConfiguration()
        content.attributedText = NSAttributedString(string: data.name)
        cell.contentConfiguration = content
        return cell
    }
}
