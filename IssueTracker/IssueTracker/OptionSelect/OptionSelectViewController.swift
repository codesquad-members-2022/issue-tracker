import UIKit
import SnapKit

protocol OptionSelectViewControllerDelegate: AnyObject {
    func selected(item: Optionable, option: Option)
}

class OptionSelectViewController: UIViewController {
    
    weak var delegate: OptionSelectViewControllerDelegate?
    
    private let model: OptionSelectModel
    
    private let tableViewCellIdentifier = "tableViewCellIdentifier"
    private let option: Option
    private let repo: Repository
    
    init(model: OptionSelectModel, option: Option, repo: Repository) {
        self.model = model
        self.option = option
        self.repo = repo
        super.init(nibName: nil, bundle: nil)
    }
    
    required convenience init?(coder: NSCoder) {
        let repo = Repository(name: "", owner: Owner(login: ""))
        self.init(model:
                    OptionSelectModel(environment:
                            .init(requestRepositoryLabels: { repo, completion in },
                                  requestRepositoryMilestones: { repo, completion in },
                                  requestRepositoryAssigness:  { repo, completion in }
                                 )
                    ),
                  option: .label,
                  repo: repo
        )
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
        self.view.backgroundColor = .white
        
        model.requestOptions(option, repo: repo)
        model.updatedOptions = {
            DispatchQueue.main.async { [weak self] in
                self?.reloadData()
            }
        }
    }
    
    private func setupViews() {
        view.addSubview(tableView)
        tableView.snp.makeConstraints { make in
            make.edges.equalToSuperview()
        }
    }
    
    func reloadData() {
        self.tableView.reloadData()
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
        let selectedItem = model.getOption(index: indexPath.row)
        delegate?.selected(item: selectedItem, option: option)
        // 아래 코드를 delegate로 coordinator에게 보내기
//
//        delegate?.selected(item: selectedItem, option: option)
//        self.navigationController?.popViewController(animated: true)
    }
}

extension OptionSelectViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return model.getOptionsCount()
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: tableViewCellIdentifier,
                                                 for: indexPath)
        var content = cell.defaultContentConfiguration()
        content.attributedText = NSAttributedString(string: model.getOption(index: indexPath.row).subTitle)
        cell.contentConfiguration = content
        return cell
    }
}
