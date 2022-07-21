import UIKit
import SnapKit

class ReposViewController: UIViewController {
    
    private let model: ReposModel
    private let tableViewCellIdentifier = "tableViewCellIdentifier"
    
    init(model: ReposModel) {
        self.model = model
        super.init(nibName: nil, bundle: nil)
    }

    required convenience init?(coder: NSCoder) {
        self.init(model: ReposModel(environment: .init(requestRepos: { completion in
        })))
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupViews()
        // 뷰컨 자신에 대한 셋팅 코드는 뷰컨을 만드는 Container에서 해준다 (추후 Coordinator로 분리)
        self.view.backgroundColor = .white
    }
    
    func reloadTableView() {
        tableView.reloadData()
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
        let selectedItem = model.getViewData(index: indexPath.row)
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
