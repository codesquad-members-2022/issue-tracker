import UIKit
import SnapKit

class ReposViewController: UIViewController {
    
    private let service: IssueService
    private let tableViewCellIdentifier = "tableViewCellIdentifier"
    private var options: [Repository]?
    
    init(service: IssueService) {
        self.service = service
        super.init(nibName: nil, bundle: nil)
    }

    required convenience init?(coder: NSCoder) {
        self.init(service: IssueService(token: ""))
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
        self.view.backgroundColor = .white
        fetchViewData()
    }
    
    private func fetchViewData() {
        service.requestRepos() { [weak self] result in
            switch result {
            case .success(let repositoryList):
                self?.options = repositoryList
                self?.tableView.reloadData()
            case .failure(let error):
                print(error)
            }
        }
    }
    
    private func setupViews() {
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
        guard let options = options else {
            return
        }
        let selectedItem = options[indexPath.row]
        guard let appdelegate = UIApplication.shared.delegate as? AppDelegate else {
            return
        }
        guard let viewController = appdelegate.container.buildViewController(.issue( selectedRepo: selectedItem)) as? IssueViewController else {
            return
        }
        self.navigationController?.pushViewController(viewController, animated: true)
    }
}

extension ReposViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        guard let options = options else {
            return 0
        }
        return options.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let options = options else {
            return UITableViewCell()
        }
        let cell = tableView.dequeueReusableCell(withIdentifier: tableViewCellIdentifier,
                                                 for: indexPath)
        var content = cell.defaultContentConfiguration()
        content.attributedText = NSAttributedString(string: options[indexPath.row].name)
        cell.contentConfiguration = content
        return cell
    }
}
