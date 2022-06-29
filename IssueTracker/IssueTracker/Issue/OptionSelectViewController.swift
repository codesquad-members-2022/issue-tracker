import UIKit
import SnapKit

protocol OptionSelectDelegate: AnyObject {
    func selected(item: Repository, option: Option)
}

class OptionSelectViewController: UIViewController {
    
    weak var delegate: OptionSelectDelegate?
    
    private let service = IssueService()
    private let token: String
    private let tableViewCellIdentifier = "tableViewCellIdentifier"
    private let option: Option
    private var options: [Repository]?
    
    init(token: String, option: Option) {
        self.token = token
        self.option = option
        super.init(nibName: nil, bundle: nil)
    }

    required convenience init?(coder: NSCoder) {
        self.init(token: "", option: .label)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
        self.view.backgroundColor = .white
        fetchViewData()
    }
    
    private func fetchViewData() {
        guard let token = GithubUserDefaults.getToken() else {
            return
        }
        service.requestRepos(accessToken: token) { [weak self] result in
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

extension OptionSelectViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        guard let options = options else {
            return
        }
        let selectedItem = options[indexPath.row]
        delegate?.selected(item: selectedItem, option: option)
        self.navigationController?.popViewController(animated: true)
    }
}

extension OptionSelectViewController: UITableViewDataSource {
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
