import UIKit
import SnapKit

protocol OptionSelectDelegate: AnyObject { // 이벤트 연결의 근-본
    func selected(item: String)
}

class OptionSelectViewController: UIViewController {
    
    private let service = IssueService()
    private var token: String?
    private var options: [Repository]?
    
    weak var delegate: OptionSelectDelegate? // 순환참조를 막기 위해 weak var로 선언
    private let tableViewCellIdentifier = "tableViewCellIdentifier"
    
    init(token: String, options: [Repository]) {
        super.init(nibName: nil, bundle: nil)
        self.token = token
        self.options = options
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
        self.view.backgroundColor = .white
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
        delegate?.selected(item: selectedItem.name) // 이벤트 보내기
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
