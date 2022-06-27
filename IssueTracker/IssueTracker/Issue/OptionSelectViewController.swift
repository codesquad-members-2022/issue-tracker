import UIKit
import SnapKit

class OptionSelectViewController: UIViewController {

    private let dummy = ["bug", "feature", "document"]
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
        // TODO: - 선택한 옵션을 이전 ViewController 에 넘겨주기
        print(indexPath.row)
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
