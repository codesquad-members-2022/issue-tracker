import UIKit
import SnapKit

protocol OptionSelectDelegate: AnyObject {
    func selected(item: Repository, option: Option)
}

class OptionSelectViewController: UIViewController {
    
    weak var delegate: OptionSelectDelegate?
    
    private let model: OptionSelectModel
    
    private let tableViewCellIdentifier = "tableViewCellIdentifier"
    private let option: Option
    
    
    init(model: OptionSelectModel, option: Option) {
        self.model = model
        self.option = option
        super.init(nibName: nil, bundle: nil)
    }

    required convenience init?(coder: NSCoder) {
        let service = IssueService(token: "")
        self.init(model: OptionSelectModel(service: service), option: .label)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
        self.view.backgroundColor = .white
        model.requestRepos()
    }
    
    private func setupViews() {
        view.addSubview(tableView)
        tableView.snp.makeConstraints { make in
            make.edges.equalToSuperview()
        }
    }
    
    private func bind() {
        model.updatedOptions = {
            self.tableView.reloadData()
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
        let selectedItem = model.getOption(index: indexPath.row)
        delegate?.selected(item: selectedItem, option: option)
        self.navigationController?.popViewController(animated: true)
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
        content.attributedText = NSAttributedString(string: model.getOption(index: indexPath.row).name)
        cell.contentConfiguration = content
        return cell
    }
}
