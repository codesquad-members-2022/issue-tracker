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
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
//        self.view.backgroundColor = .white
    }
    
    func reloadTableView() {
        tableView.reloadData()
    }
    
    func setupViews() {
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
        self.delegate?.showIssue(didSelectRowAt: indexPath)
        // 아래 코드를 위 한줄로 대체
//        let selectedItem = model.getViewData(index: indexPath.row)
//        guard let appdelegate = UIApplication.shared.delegate as? AppDelegate else {
//            return
//        }
//        guard let viewController = appdelegate.container.buildViewController(.issue( selectedRepo: selectedItem)) as? IssueViewController else {
//            return
//        }
//        self.navigationController?.pushViewController(viewController, animated: true)
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
