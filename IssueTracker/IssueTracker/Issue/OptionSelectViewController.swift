import UIKit
import SnapKit

protocol OptionSelectDelegate: AnyObject { // 이벤트 연결의 근-본
    func selected(item: String)
}

class OptionSelectViewController: UIViewController {
    
    weak var delegate: OptionSelectDelegate? // 순환참조를 막기 위해 weak var로 선언
    private let dummy = ["issue-tracker", "banchan", "starbuckst"]
    private let tableViewCellIdentifier = "tableViewCellIdentifier"
    
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
        let selectedItem = dummy[indexPath.row]
        delegate?.selected(item: selectedItem) // 이벤트 보내기
        self.navigationController?.popViewController(animated: true)
    }
}

extension OptionSelectViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dummy.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: tableViewCellIdentifier,
                                                 for: indexPath)
        var content = cell.defaultContentConfiguration()
        content.attributedText = NSAttributedString(string: dummy[indexPath.row])
        cell.contentConfiguration = content
        return cell
    }
}
