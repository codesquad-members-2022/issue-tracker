//
//  NewIssueViewController.swift
//  IssueTracker
//
//  Created by Bibi on 2022/06/22.
//

import UIKit
import SwiftUI

class NewIssueViewController: UIViewController {
    
    private let service = IssueService()
    
    private let optionList = Option.allCases
    private var selectedList = Array<String>(repeating: "", count: Option.allCases.count)
    
    enum Option: CaseIterable {
        case repository
        case label
        case milestone
        case assignee
        
        var description: String {
            switch self {
            case .repository:
                return "저장소"
            case .label:
                return "레이블"
            case .milestone:
                return "마일스톤"
            case .assignee:
                return "담당자"
            }
        }
    }
    
    private lazy var navSegmentedControl: UISegmentedControl = {
        let buttonList = ["마크다운", "미리보기"]
        var control = UISegmentedControl(items: buttonList)
        
        return control
    }()
    
    private lazy var titleLabel: UILabel = {
        var label = UILabel()
        label.text = "제목"
        label.font = UIFont.systemFont(ofSize: 20, weight: .bold)
        return label
    }()
    
    private lazy var titleField: UITextField = {
        var textField = UITextField()
        return textField
    }()
    
    private lazy var contentField: UITextView = {
        var contentField = UITextView()
        return contentField
    }()
    
    private lazy var horizontalDevider: UIView = {
        var devider = Devider(direction: .horizontal(width: self.view.bounds.width), color: .systemGray)
        return devider
    }()
    
    private let optionTableCellIdentifier = "optionTableCellIdentifier"
    
    private lazy var optionTable: UITableView = {
        var tableView = UITableView()
        tableView.delegate = self
        tableView.dataSource = self
        tableView.register(UITableViewCell.self,
                           forCellReuseIdentifier: optionTableCellIdentifier)
        return tableView
    }()
    
    private lazy var optionTableHeader: UILabel = {
        var label = UILabel()
        label.text = "추가옵션"
        label.font = UIFont.systemFont(ofSize: 20, weight: .bold)
        label.textAlignment = .left
        return label
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupNavigationBar()
        setUpViews()
    }

    private func setupNavigationBar() {
        self.navigationItem.titleView = navSegmentedControl
        self.navigationItem.rightBarButtonItem = UIBarButtonItem(customView: createButton)
    }
    
    private func setUpViews() {
        self.view.backgroundColor = .white
        
        self.view.addSubview(titleLabel)
        titleLabel.snp.makeConstraints { make in
            make.top.leading.equalTo(self.view.safeAreaLayoutGuide).offset(10)
            make.width.equalTo(50)
        }
        
        self.view.addSubview(titleField)
        titleField.snp.makeConstraints { make in
            make.top.trailing.equalTo(self.view.safeAreaLayoutGuide).offset(10)
            make.leading.equalTo(titleLabel.snp.trailing)
        }
        
        self.view.addSubview(horizontalDevider)
        horizontalDevider.snp.makeConstraints { make in
            make.top.equalTo(titleLabel.snp.bottom).offset(5)
            make.leading.equalTo(self.view.safeAreaLayoutGuide).offset(5)
            make.trailing.equalTo(self.view.safeAreaLayoutGuide).offset(-5)
            make.height.equalTo(1)
        }
        
        optionTable.tableHeaderView = optionTableHeader
        optionTable.tableHeaderView?.frame.size.height = 30
        
        self.view.addSubview(optionTable)
        optionTable.isScrollEnabled = true
        optionTable.snp.makeConstraints { make in
            make.leading.trailing.bottom.equalTo(self.view.safeAreaLayoutGuide)
            make.height.equalTo(optionTable
                .contentSize
                .height + 30)
        }
        
        self.view.addSubview(contentField)
        contentField.snp.makeConstraints { make in
            make.top.equalTo(horizontalDevider.snp.bottom)
            make.leading.trailing.equalTo(self.view.safeAreaLayoutGuide)
            make.bottom.equalTo(optionTable.snp.top)
        }
    }

    private lazy var createButton: UIButton = {
        var configuration = UIButton.Configuration.plain()
        var container = AttributeContainer()
        container.font = UIFont.systemFont(ofSize: 14)
        configuration.attributedTitle = AttributedString("저장", attributes: container)
        
        configuration.buttonSize = .small
        configuration.image = UIImage(systemName: "folder.badge.plus")
        configuration.imagePadding = 4
        let button = UIButton(configuration: configuration, primaryAction: UIAction(handler: { action in
            self.touchedCreateButton()
        }))
        return button
    }()
    
    private func touchedCreateButton() {
        // TODO: 이슈생성
        //1. api 호출
        //2. api 가 성공적으로 응답을 보내줬다면 =>
            //2-1. 이전 화면으로 돌아가고
            //2-2. 이슈 목록 조회 다시해서 보여주기
        //3. api 가 실패했다면 => issue 생성실패 얼럿띄우기
    }
}

extension NewIssueViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        switch optionList[indexPath.row] {
        case .repository:
            // TODO: issueService의 requestRepos() 연결해서 저장소목록 보여주기
            guard let appdelegate = UIApplication.shared.delegate as? AppDelegate,
                  let token = GithubUserDefaults.getToken() else {
                return
            }
            
            service.requestRepos(accessToken: token) { result in
                switch result {
                case .success(let repositoryList):
                    //repositoryList를 OptionSelectViewController에 전달해 주어야 함
                    print(repositoryList)
                    guard let viewController = appdelegate.container.buildViewController(.optionSelect(token: token, repositories: repositoryList)) as? OptionSelectViewController else {
                        return
                    }
                    self.navigationController?.pushViewController(viewController, animated: true)
                    viewController.delegate = self
                case .failure(let error):
                    print(error)
                }
            }
            
            
        default:
            break
        }
        print(optionList[indexPath.row])
    }
}

extension NewIssueViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return optionList.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: optionTableCellIdentifier,
                                                 for: indexPath)
        var sidebarCell = UIListContentConfiguration.sidebarCell()
        sidebarCell.text = optionList[indexPath.item].description
        sidebarCell.secondaryText = selectedList[indexPath.item]
        sidebarCell.prefersSideBySideTextAndSecondaryText = true
        
        cell.contentConfiguration = sidebarCell
        cell.accessoryType = .disclosureIndicator
        return cell
    }
    
}

extension NewIssueViewController: OptionSelectDelegate {
    func selected(item: String) {
        selectedList[0] = item
        self.optionTable.reloadData()
    }
}
